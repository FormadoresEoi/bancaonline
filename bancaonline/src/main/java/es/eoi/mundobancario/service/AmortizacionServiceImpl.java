package es.eoi.mundobancario.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.enums.Tipos;
import es.eoi.mundobancario.repository.AmortizacionRepository;

@Service
@Transactional
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
	public List<Amortizacion> calcularAmortizaciones(Prestamo prestamo) {
		List<Amortizacion> amortizaciones = new ArrayList<Amortizacion>();
		Timestamp timestamp = new Timestamp(prestamo.getFecha().getTime());
		
		double importe = prestamo.getImporte() / prestamo.getPlazos();
		
		for (int i = 1; i <= prestamo.getPlazos(); i++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timestamp.getTime());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.add(Calendar.MONTH, 1);
			
			Amortizacion amortizacion = new Amortizacion();
			amortizacion.setImporte(importe);
			amortizacion.setId_prestamo(prestamo.getId());
			amortizacion.setPrestamo(prestamo);
			timestamp = new Timestamp(cal.getTime().getTime());
			amortizacion.setFecha(timestamp);
			amortizaciones.add(amortizacion);
		}
		return amortizaciones;
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
		
		a.getPrestamo().getAmortizaciones()
						.stream()
						.filter(am -> am.equals(a)).findFirst().get().setEstado("PAGADO");
		
		
		amortizacionRepository.save(a);
		
		Tipos tipo = Tipos.Amortizacion;
		TipoMovimiento tipoMov = new TipoMovimiento();
		tipoMov.setId(tipo.getEnumCode());
		tipoMov.setTipo(tipo.getEnumDesc());
		movimientoService.RealizarMovimiento(
				-a.getImporte(), 
				a.getPrestamo().getCuenta(),
				a.getFecha(), 
				a.getPrestamo().getDescripcion(),
				tipoMov);
		
		tipo = Tipos.Interes;
		tipoMov.setId(tipo.getEnumCode());
		tipoMov.setTipo(tipo.getEnumDesc());
		movimientoService.RealizarMovimiento(
				-(a.getImporte() * 1.02), 
				a.getPrestamo().getCuenta(),
				a.getFecha(), 
				a.getPrestamo().getDescripcion(),
				tipoMov);
		
	}
	

}
