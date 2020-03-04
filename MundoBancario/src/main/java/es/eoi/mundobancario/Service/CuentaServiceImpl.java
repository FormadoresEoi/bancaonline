package es.eoi.mundobancario.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.Repository.MovimientoRepository;
import es.eoi.mundobancario.Repository.PrestamoRepository;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.enums.TiposMovimiento;

@Service
public class CuentaServiceImpl implements CuentaService {
	@Autowired
	CuentasRepository cuentasRepository;
	@Autowired
	MovimientoRepository movimientoRepository;
	@Autowired
	PrestamoRepository prestamoRepository;
	
	TiposMovimiento tipo;
	
	//TODO añadir excepcion
	private Cuenta checkNull(Optional<Cuenta> cuenta) {
		if(cuenta.isPresent()) {
			return cuenta.get();
		}else {
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

	public Movimiento createPagos(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}
	
	public Movimiento createIngresos(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}
	
	//TODO añadir las amortizaciones
	public Prestamo createPrestamos(Prestamo prestamo, Movimiento movimiento, int id) {
		Date fecha = new Date();
		int mes = fecha.getMonth();
		List<Amortizacion> amortizaciones = new ArrayList<Amortizacion>();
		for(int i = 0; i < prestamo.getPlazo(); i++) {
			fecha.setMonth(mes + i);
			amortizaciones.add(new Amortizacion(fecha, (prestamo.getImporte()/4)));
		}
		prestamo.setAmortizacion(amortizaciones);
		if(prestamoRepository.save(prestamo) != null) {
			movimientoRepository.save(movimiento).setTipoMovimiento(tipo.PRESTAMO);
			Cuenta cuenta = checkNull(cuentasRepository.findById(id));
			cuenta.getPrestamo().add(prestamo);
			cuenta.getMovimiento().add(movimiento);
			update(cuenta);
			return prestamo;
		}else
			return null;
	}
	
	//TODO arreglar
	public Cuenta findPrestamosAmortizados(int id) {
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			for (Amortizacion amortizacion : prestamo.getAmortizacion()) {
				if(amortizacion.getFecha().compareTo(new Date()) <= 0)
					return cuenta;
			}
		}
		return null;
	}
	//TODO arreglar
	public Cuenta findPrestamosVivos(int id) {
		Cuenta cuenta = checkNull(cuentasRepository.findById(id));
		for (Prestamo prestamo : cuenta.getPrestamo()) {
			for (Amortizacion amortizacion : prestamo.getAmortizacion()) {
				if(amortizacion.getFecha().compareTo(new Date()) > 0)
					return cuenta;
			}
		}
		return null;
	}
	
	public List<Cuenta> findAllDeudora() {
		
//		List<Cuenta> cuentas = new ArrayList<Cuenta>();
//		for (Cuenta cuenta : cuentasRepository.findAll()) {
//			if(cuenta.getSaldo() < 0)
//				cuentas.add(cuenta);
//		}
//		return cuentas;
		return cuentasRepository.findAllBySaldoLessThan(0);
	}

	public List<Cuenta> findAll() {
		return cuentasRepository.findAll();
	}
	
	public void ejecutarAmortizacionsDiarias() {
		// TODO Auto-generated method stub
	}
}
