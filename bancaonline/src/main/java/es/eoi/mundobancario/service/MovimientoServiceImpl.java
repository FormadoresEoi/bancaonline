package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository movimientoRepository;
	
	@Override
	public void create(Movimiento movimiento) {
		movimientoRepository.save(movimiento);
	}

	@Override
	public Optional<Movimiento> findById(int id) {
		return movimientoRepository.findById(id);
	}

	@Override
	public List<Movimiento> findAll() {
		return movimientoRepository.findAll();
	}

	@Override
	public List<Movimiento> findAllByCuentasNumCuenta(int NumCuenta) {
		return movimientoRepository.findAllByCuentasNumCuenta(NumCuenta);
	}
}
