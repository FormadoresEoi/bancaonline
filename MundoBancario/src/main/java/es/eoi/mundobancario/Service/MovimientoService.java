package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	Movimiento CreateMovimiento(Movimiento movimiento);

	Optional<Movimiento> findByMovimientoId(int id);

	Movimiento updateMovimiento(Movimiento movimiento);

	void removeMovimiento(int id);
}
