package es.eoi.mundobancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {

	@Autowired
	AmortizacionRepository repository;

	public Amortizacion FindById(int id) {
		return repository.findById(id).get();
	}

	@Override
	public Amortizacion createAmortizacion(Amortizacion amortizacion) {
		return repository.save(amortizacion);
	}

	@Override
	public List<Amortizacion> listAmortizaciones() {
		return repository.findAll();
	}
}