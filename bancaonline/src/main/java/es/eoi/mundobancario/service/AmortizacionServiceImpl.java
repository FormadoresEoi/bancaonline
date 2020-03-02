package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.repository.AmortizacionRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService{

	@Autowired
	AmortizacionRepository amortizacionRepository;
	
	@Override
	public void create(Amortizacion amortizacion) {
		amortizacionRepository.save(amortizacion);
	}

	@Override
	public Optional<Amortizacion> findById(int id) {
		return amortizacionRepository.findById(id);
	}

	@Override
	public List<Amortizacion> findAll() {
		return amortizacionRepository.findAll();
	}

}
