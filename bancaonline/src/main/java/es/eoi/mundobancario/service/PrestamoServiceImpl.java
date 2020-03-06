package es.eoi.mundobancario.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.enums.Tipos;
import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService{

	@Autowired
	PrestamoRepository prestamoRepository;
	
	@Autowired
	CuentaService cuentaService;
	
	@Autowired
	MovimientoService movimientoService;
	
	@Override
	public void create(Prestamo prestamo) {
		Timestamp timestamp = new Timestamp(prestamo.getFecha().getTime());
		System.out.println(timestamp);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		prestamo.setFecha(new Timestamp(cal.getTime().getTime()));

		prestamo.setEstado("PENDIENTE");
		
		prestamoRepository.save(prestamo);
		
		Tipos tipo = Tipos.Prestamo;
		TipoMovimiento tipoMov = new TipoMovimiento();
		tipoMov.setId(tipo.getEnumCode());
		tipoMov.setTipo(tipo.getEnumDesc());
		movimientoService.RealizarMovimiento(
				prestamo.getImporte(), 
				prestamo.getCuenta(),
				prestamo.getFecha(), 
				prestamo.getDescripcion(),
				tipoMov);
	}

	@Override
	public Optional<Prestamo> findByCuentaId(int id) {
		return prestamoRepository.findByCuenta(id);
	}

	@Override
	public Optional<Prestamo> findById(int id){
		return prestamoRepository.findById(id);
	}
	
	@Override
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@Override
	public List<Prestamo> findAllByCuenta(int id) {
		return prestamoRepository.findAllByCuentaNumCuenta(id);
	}
		
	@Override
	public List<Prestamo> findAllByCuentaIdVivos(int id) {
		 List<Prestamo> prestamos=prestamoRepository.findAllByCuentaNumCuenta(id);
		 List<Prestamo> prestamosVivos = new ArrayList<>();		 
		 
		 boolean isVivo;
		 for (Prestamo prestamo : prestamos) {
			 isVivo = false;
			for (Amortizacion a : prestamo.getAmortizaciones()) {
				
				if(a.getFecha().after(Calendar.getInstance().getTime())) {
					isVivo = true;
				}
			}
			if(isVivo)
				prestamosVivos.add(prestamo);
		}
		 
		 return prestamosVivos;
	}
	
	@Override
	public List<Prestamo> findAllByCuentaIdAmortizados(int id) {
		return this.findAllByCuenta(id)
				.stream()
				.filter(p -> p.getAmortizaciones().size() >= p.getPlazos())
				.collect(Collectors.toList());
	}
	
	public List<Prestamo> findAllVivos() {
		 List<Prestamo> prestamos=prestamoRepository.findAll();
		 List<Prestamo> prestamosVivos = new ArrayList<>();
		 
		 boolean isVivo;
		 for (Prestamo prestamo : prestamos) {
			 isVivo = false;
			for (Amortizacion a : prestamo.getAmortizaciones()) {
				
				if(a.getFecha().after(Calendar.getInstance().getTime())) {
					isVivo = true;
				}
			}
			if(isVivo)
				prestamosVivos.add(prestamo);
		}
		 
		 return prestamosVivos;
	}
	
}
