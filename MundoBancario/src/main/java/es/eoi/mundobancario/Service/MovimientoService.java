package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	Movimiento Create(Movimiento movimiento);

	Optional<Movimiento> findById(int id);

	Movimiento update(Movimiento movimiento);

	void remove(int id);
}
