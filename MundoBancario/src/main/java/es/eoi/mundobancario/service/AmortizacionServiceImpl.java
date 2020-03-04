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

	@Override
	public Amortizacion getById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Amortizacion> getAll() {
		return repository.findAll();
	}

	@Override
	public boolean post(Amortizacion amortizacion) {
		repository.save(amortizacion);
		return true;
	}

}
