package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {

	@Autowired
	AmortizacionRepository amortrepo;

	@Override
	public List<Amortizacion> MostrarAmortizacion() {
		return amortrepo.findAll();
	}

	@Override
	public Amortizacion CrearAmortizacion(Amortizacion amortizacion) {
		return amortrepo.save(amortizacion);
	}

	@Override
	public Optional<Amortizacion> buscarAmortizacion(int id) {

		return amortrepo.findById(id);

	}

	@Override
	public Amortizacion updateAmortizacion(Amortizacion amortizacion) {
		return amortrepo.save(amortizacion);
	}

	@Override
	public void removeAmortizacion(int id) {
		amortrepo.deleteById(id);
	}
}