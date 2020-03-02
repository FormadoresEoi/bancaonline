package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Amortizacion;

public interface AmortizacionService {

	Amortizacion CreateAmortizacion(Amortizacion amortizacion);

	Optional<Amortizacion> findAmortizacionById(int id);

	Amortizacion updateAmortizacion(Amortizacion amortizacion);

	void removeAmortizacion(int id);
}
