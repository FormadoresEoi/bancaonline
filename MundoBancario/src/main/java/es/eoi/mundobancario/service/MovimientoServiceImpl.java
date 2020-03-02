package es.eoi.mundobancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository repository;
	
	
	@Override
	public Movimiento FindById(int id) {
		return repository.findById(id).get();
	}

	@Override
	public Movimiento createMovimiento(Movimiento movimiento) {
		return repository.save(movimiento);
	}

	@Override
	public List<Movimiento> listMovimiento() {
		return repository.findAll();
	}

}
