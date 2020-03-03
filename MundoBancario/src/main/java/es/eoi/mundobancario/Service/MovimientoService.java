package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	Movimiento CreateCuenta(Movimiento movimiento);

	Optional<Movimiento> findByMovimientoId(int id);

	Movimiento updateCuenta(Movimiento movimiento);

	void removeCuenta(int id);
}
