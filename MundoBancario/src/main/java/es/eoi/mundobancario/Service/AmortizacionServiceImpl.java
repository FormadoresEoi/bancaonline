package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.Repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {

	@Autowired
	AmortizacionRepository amortizacionrepository;

	@Override
	public Amortizacion CreateAmortizacion(Amortizacion amortizacion) {
		return amortizacionrepository.save(amortizacion);
	}

	@Override
	public Optional<Amortizacion> findAmortizacionById(int id) {

		return amortizacionrepository.findById(id);

	}

	@Override
	public Amortizacion updateAmortizacion(Amortizacion amortizacion) {
		return amortizacionrepository.save(amortizacion);
	}

	@Override
	public void removeAmortizacion(int id) {
		amortizacionrepository.deleteById(id);
	}
}
