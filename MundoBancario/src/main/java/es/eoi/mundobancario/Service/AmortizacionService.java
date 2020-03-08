package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Amortizacion;

public interface AmortizacionService {

	Amortizacion Create(Amortizacion amortizacion);

	Optional<Amortizacion> findById(int id);

	Amortizacion update(Amortizacion amortizacion);

	void remove(int id);
}
