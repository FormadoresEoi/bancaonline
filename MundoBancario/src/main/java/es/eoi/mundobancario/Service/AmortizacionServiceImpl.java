package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.Repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {
	@Autowired
	AmortizacionRepository amortizacionRepository;

	public Amortizacion CreateAmortizacion(Amortizacion amortizacion) {
		return amortizacionRepository.save(amortizacion);
	}

	public Optional<Amortizacion> findAmortizacionById(int id) {
		return amortizacionRepository.findById(id);

	}

	public Amortizacion updateAmortizacion(Amortizacion amortizacion) {
		return amortizacionRepository.save(amortizacion);
	}

	public void removeAmortizacion(int id) {
		amortizacionRepository.deleteById(id);
	}
}
