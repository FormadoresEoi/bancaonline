package es.eoi.mundobancario.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.AmortizacionRepository;
import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.Repository.MovimientoRepository;
import es.eoi.mundobancario.Repository.PrestamoRepository;
import es.eoi.mundobancario.Repository.TipoMovimientoRepository;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
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
	@Autowired
	TipoMovimientoRepository tipoMovimientoRepository;

	TiposMovimiento tipo;

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
			double saldo = cuenta.getSaldo();
			if (saldo < movimiento.getImporte())
				throw new NotMoneyEnoughtException();
			cuenta.setSaldo(saldo - movimiento.getImporte());
//			TipoMovimiento tipoMov = tipoMovimientoRepository.findByTipo("PAGO").get();
//			movimiento.setTipoMovimiento(tipoMov);
			movimiento.setCuenta(cuenta);
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
			//movimiento.setTipoMovimiento(new TipoMovimiento(tipo.INGRESO));
			movimiento.setCuenta(cuenta);
			return movimientoRepository.save(movimiento);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	@Transactional
	public Prestamo createPrestamos(Prestamo prestamo, Movimiento movimiento, int id) {
		List<Amortizacion> amortizaciones = new ArrayList<Amortizacion>();
		if (prestamo.getPlazo() > 0) {
			Calendar fecha = Calendar.getInstance();
			int mes = fecha.get(Calendar.MONTH) + 1;
			Cuenta cuenta = checkNull(cuentasRepository.findById(id));
			double saldo = cuenta.getSaldo();
			prestamo.setCuenta(cuenta);
		
			for (int i = 0; i < prestamo.getPlazo(); i++) {
				fecha.set(Calendar.MONTH, (mes + i));
				Amortizacion amortizacion = new Amortizacion(fecha, (prestamo.getImporte() / prestamo.getPlazo()));
				amortizaciones.add(amortizacion);
			}
			prestamo.setAmortizacion(amortizaciones);
			prestamo = prestamoRepository.save(prestamo);
			for (Amortizacion amortizacion : amortizaciones) {
				amortizacion.setPrestamo(prestamo);
				amortizacionRepository.save(amortizacion);
			}
			//movimiento.setTipoMovimiento(new TipoMovimiento(tipo.PRESTAMO));
			movimiento.setCuenta(cuenta);
			movimientoRepository.save(movimiento);
			cuenta.setSaldo(saldo + prestamo.getImporte());
			update(cuenta);
			return prestamo;
		}
		return null;
	}
	
	public Cuenta findPrestamosAmortizados(int id) {
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			for (Amortizacion amortizacion : prestamo.getAmortizacion()) {
				if (amortizacion.getFecha().getTime().compareTo(Calendar.getInstance().getTime()) <= 0)
					amortizacion.setPrestamo(prestamo);
			}
			prestamo.setCuenta(cuenta);
		}
		return cuenta;
	}
	
	public Cuenta findPrestamosVivos(int id) {
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			for (Amortizacion amortizacion : prestamo.getAmortizacion()) {
				if (amortizacion.getFecha().getTime().compareTo(Calendar.getInstance().getTime()) > 0)
					amortizacion.setPrestamo(prestamo);
			}
			prestamo.setCuenta(cuenta);
		}
		return cuenta;
	}

	public List<Cuenta> findAllDeudora() {
		return cuentasRepository.findAllBySaldoLessThan(0.0);
	}

	public List<Cuenta> findAll() {
		return cuentasRepository.findAll();
	}
	
	public void ejecutarAmortizacionsDiarias() {
		List<Amortizacion> amortizaciones = amortizacionRepository.findAllByFechaEquals(Calendar.getInstance().getTime());
		for (Amortizacion amortizacion : amortizaciones) {
			Cuenta cuenta = checkNull(
					cuentasRepository.findById(amortizacion.getPrestamo().getCuenta().getNumeroCuenta()));
			double saldo = cuenta.getSaldo();
			Movimiento movimiento = new Movimiento("pago de amortizacion", Calendar.getInstance(), amortizacion.getImporte());
			//movimiento.setTipoMovimiento(new TipoMovimiento(tipo.AMORTIZACION));
			movimiento.setCuenta(cuenta);
			movimientoRepository.save(movimiento);
			Movimiento interes = new Movimiento("pago de intereses", Calendar.getInstance(), (amortizacion.getImporte() * 0.02));
			//movimiento.setTipoMovimiento(new TipoMovimiento(tipo.INTERES));
			movimiento.setCuenta(cuenta);
			movimientoRepository.save(interes);
			cuenta.setSaldo(saldo - amortizacion.getImporte() - (amortizacion.getImporte() * 0.02));
			cuentasRepository.save(cuenta);
		}
	}
}
