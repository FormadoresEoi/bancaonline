package es.eoi.mundobancario.repository;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Amortizacion;

public interface AmortizacionService {
	
	void create(Amortizacion amortizacion);

	Optional<Amortizacion> findById(int id);

	List<Amortizacion> findAll();
}
