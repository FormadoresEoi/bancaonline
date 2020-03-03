package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.MovimientoRepository;
import es.eoi.mundobancario.entity.Movimiento;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository repository;

	@Override
	public Movimiento CreateCuenta(Movimiento movimiento) {
		return repository.save(movimiento);
	}

	@Override
	public Optional<Movimiento> findByMovimientoId(int id) {
		return null;
	}

	@Override
	public Movimiento updateCuenta(Movimiento movimiento) {
		return repository.save(movimiento);
	}

	@Override
	public void removeCuenta(int id) {
		repository.deleteById(id);
	}
}