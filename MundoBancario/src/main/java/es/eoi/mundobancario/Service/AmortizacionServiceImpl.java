package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.Repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {

	AmortizacionRepository amortizacionrepository;

	public Amortizacion CreateAmortizacion(Amortizacion amortizacion) {
		return amortizacionrepository.save(amortizacion);
	}

	public Optional<Amortizacion> findAmortizacionById(int id) {
		return amortizacionrepository.findById(id);

	}

	public Amortizacion updateAmortizacion(Amortizacion amortizacion) {
		return amortizacionrepository.save(amortizacion);
	}

	public void removeAmortizacion(int id) {
		amortizacionrepository.deleteById(id);
	}
}
