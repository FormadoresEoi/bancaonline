package es.eoi.mundobancario.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {

	@Autowired
	AmortizacionRepository amortizacionRepository;

	@Autowired
	MovimientoService movimientoService;
	
	@Autowired
	CuentaService cuentaService;

	@Override
	public void create(Amortizacion amortizacion) {
		amortizacionRepository.save(amortizacion);
	}

	@Override
	public Optional<Amortizacion> findById(int id) {
		return amortizacionRepository.findById(id);
	}

	@Override
	public List<Amortizacion> findAll() {
		return amortizacionRepository.findAll();
	}

	@Override
	public void calcularAmortizaciones(Prestamo prestamo) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		double importe = prestamo.getImporte() / prestamo.getPlazos();

		for (int i = 1; i <= prestamo.getPlazos(); i++) {
			Amortizacion amortizacion = new Amortizacion();
			amortizacion.setImporte(importe);
			amortizacion.setPrestamo(prestamo);
			cal.add(Calendar.MONTH, i);
			amortizacion.setFecha(new Timestamp(cal.getTimeInMillis()));
			System.out.println(amortizacion.getFecha());
			create(amortizacion);
		}
	}


	@Override
	public void ejecutarAmortizacionesDiarias() {
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		findAll().stream()
				 .filter(a -> a.getFecha().compareTo(cal.getTime()) == 0)
				 .findFirst()
				 .ifPresent(a -> amortizar(a));
				 
	}
	
	@Override
	public void amortizar(Amortizacion a) {
		Cuenta cuenta = a.getPrestamo().getCuenta();
		cuenta.setSaldo(cuenta.getSaldo() + (-(a.getPrestamo().getImporte() * 1.02)));
		cuentaService.update(cuenta);
		
		movimientoService.RealizarMovimiento(
				-a.getImporte(), 
				a.getPrestamo().getCuenta(),
				a.getFecha(), 
				a.getPrestamo().getDescripcion(),
				TipoMovimiento.Tipos.Amortizacion);
		
		movimientoService.RealizarMovimiento(
				-(a.getImporte() * 1.02), 
				a.getPrestamo().getCuenta(),
				a.getFecha(), 
				a.getPrestamo().getDescripcion(),
				TipoMovimiento.Tipos.Interes);
		
	}
	

}
