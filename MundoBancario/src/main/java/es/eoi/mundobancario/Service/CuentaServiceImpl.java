package es.eoi.mundobancario.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.AmortizacionRepository;
import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.Repository.MovimientoRepository;
import es.eoi.mundobancario.Repository.PrestamoRepository;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.enums.TiposMovimiento;
import es.eoi.mundobancario.excepcion.NotMoneyEnoughtException;

@Service
public class CuentaServiceImpl implements CuentaService {
	@Autowired
	CuentasRepository cuentasRepository;
	@Autowired
	MovimientoRepository movimientoRepository;
	@Autowired
	PrestamoRepository prestamoRepository;
	@Autowired
	AmortizacionRepository amortizacionRepository;

	TiposMovimiento tipo;

	// TODO añadir excepcion
	private Cuenta checkNull(Optional<Cuenta> cuenta) {
		try {
			if (cuenta.isPresent()) {
				return cuenta.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Cuenta create(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public Cuenta findById(int numCuenta) {
		return checkNull(cuentasRepository.findById(numCuenta));
	}

	public Cuenta update(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public void remove(int numCuenta) {
		cuentasRepository.deleteById(numCuenta);
	}

	public Movimiento createPagos(Movimiento movimiento, int id) {
		try {
			Cuenta cuenta = checkNull(cuentasRepository.findById(id));
			List<Movimiento> movimientos = cuenta.getMovimiento();
			double saldo = cuenta.getSaldo();
			if (saldo < movimiento.getImporte())
				throw new NotMoneyEnoughtException();
			cuenta.setSaldo(saldo - movimiento.getImporte());
			movimiento.setTipoMovimiento(new TipoMovimiento(tipo.PAGO));
			movimientos.add(movimiento);
			cuenta.setMovimiento(movimientos);
			update(cuenta);
			return movimientoRepository.save(movimiento);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Movimiento createIngresos(Movimiento movimiento, int id) {
		try {
			Cuenta cuenta = checkNull(cuentasRepository.findById(id));
			double saldo = cuenta.getSaldo();
			cuenta.setSaldo(saldo + movimiento.getImporte());
			movimiento.setTipoMovimiento(new TipoMovimiento(tipo.INGRESO));
			movimiento.setCuenta(cuenta);
			return movimientoRepository.save(movimiento);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Prestamo createPrestamos(Prestamo prestamo, Movimiento movimiento, int id) {
		if (prestamo.getPlazo() > 0) {
			Date fecha = new Date();
			int mes = fecha.getMonth();
			Cuenta cuenta = checkNull(cuentasRepository.findById(id));
			List<Amortizacion> amortizaciones = new ArrayList<Amortizacion>();
			List<Prestamo> prestamos = cuenta.getPrestamo();
			List<Movimiento> movimientos = cuenta.getMovimiento();
			double saldo = cuenta.getSaldo();
			for (int i = 0; i < prestamo.getPlazo(); i++) {
				fecha.setMonth(mes + i);
				amortizaciones.add(new Amortizacion(fecha, (prestamo.getImporte() / 4)));
			}
			for (Amortizacion amortizacion : amortizaciones) {
				amortizacion.setPrestamo(prestamo);
				amortizacionRepository.save(amortizacion);
			}
//			prestamo.setAmortizacion(amortizaciones);
			prestamo.setCuenta(cuenta);
			if (prestamoRepository.save(prestamo) != null) {
				movimiento.setTipoMovimiento(new TipoMovimiento(tipo.PRESTAMO));
				movimiento.setCuenta(cuenta);
				movimientoRepository.save(movimiento);
//				prestamos.add(prestamo);
//				movimientos.add(movimiento);
//				cuenta.setMovimiento(movimientos);
//				cuenta.setPrestamo(prestamos);
				cuenta.setSaldo(saldo + prestamo.getImporte());
				update(cuenta);
				return prestamo;
			}
		} else
			return null;
		return null;
	}

	public Cuenta findPrestamosAmortizados(int id) {
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		Set<Prestamo> prestamos = new HashSet<Prestamo>();
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			for (Amortizacion amortizacion : prestamo.getAmortizacion()) {
				if (amortizacion.getFecha().compareTo(new Date()) <= 0)
					prestamos.add(prestamo);
			}
		}
		List<Prestamo> listaPrestamos = null;
		listaPrestamos.addAll(prestamos);
		cuenta.setPrestamo(listaPrestamos);
		return cuenta;
	}

	public Cuenta findPrestamosVivos(int id) {
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		Set<Prestamo> prestamos = new HashSet<Prestamo>();
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			for (Amortizacion amortizacion : prestamo.getAmortizacion()) {
				if (amortizacion.getFecha().compareTo(new Date()) > 0)
					prestamos.add(prestamo);
			}
		}
		List<Prestamo> listaPrestamos = null;
		listaPrestamos.addAll(prestamos);
		cuenta.setPrestamo(listaPrestamos);
		return cuenta;
	}

	public List<Cuenta> findAllDeudora() {
		double saldo = 0;
		return cuentasRepository.findBySaldoLessThan(saldo);
	}

	public List<Cuenta> findAll() {
		return cuentasRepository.findAll();
	}

	public void ejecutarAmortizacionsDiarias() {
		List<Amortizacion> amortizaciones = amortizacionRepository.findAllByFechaEquals(new Date());
		for (Amortizacion amortizacion : amortizaciones) {
			Cuenta cuenta = checkNull(
					cuentasRepository.findById(amortizacion.getPrestamo().getCuenta().getNumeroCuenta()));
			double saldo = cuenta.getSaldo();
			Movimiento movimiento = new Movimiento("pago de amortizacion", new Date(), amortizacion.getImporte());
			movimiento.setTipoMovimiento(new TipoMovimiento(tipo.AMORTIZACION));
			movimientoRepository.save(movimiento);
			Movimiento interes = new Movimiento("pago de intereses", new Date(), (amortizacion.getImporte() * 0.2));
			movimiento.setTipoMovimiento(new TipoMovimiento(tipo.INTERES));
			movimientoRepository.save(movimiento);
			cuenta.setSaldo(saldo - amortizacion.getImporte() - (amortizacion.getImporte() * 0.2));
		}
	}
}
