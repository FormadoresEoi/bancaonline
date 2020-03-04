package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Prestamo;

public interface AmortizacionService {
	
	void create(Amortizacion amortizacion);

	Optional<Amortizacion> findById(int id);

	List<Amortizacion> findAll();
	
	void calcularAmortizaciones(Prestamo prestamo);
	
	void ejecutarAmortizacionesDiarias();
	
	void amortizar(Amortizacion amortizacion);
}
