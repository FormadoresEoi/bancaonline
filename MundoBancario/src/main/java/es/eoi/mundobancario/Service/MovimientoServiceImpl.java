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

	public Movimiento CreateMovimiento(Movimiento movimiento) {
		return repository.save(movimiento);
	}

	public Optional<Movimiento> findByMovimientoId(int id) {
		return null;
	}

	public Movimiento updateMovimiento(Movimiento movimiento) {
		return repository.save(movimiento);
	}

	public void removeMovimiento(int id) {
		repository.deleteById(id);
	}
}