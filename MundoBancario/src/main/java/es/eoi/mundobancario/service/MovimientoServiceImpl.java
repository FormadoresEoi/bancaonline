package es.eoi.mundobancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository repository;

	@Override
	public Movimiento getById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Movimiento> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Movimiento> getByCuenta(Cuenta cuenta) {
		return repository.findByCuenta(cuenta);
	}

	@Override
	public boolean post(Movimiento movimiento) {
		repository.save(movimiento);
		return true;
	}

}
