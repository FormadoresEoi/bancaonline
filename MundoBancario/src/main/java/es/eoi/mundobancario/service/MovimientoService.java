package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	List<Movimiento> MostrarMovimiento();

	Movimiento CrearMovimiento(Movimiento movimiento);

	Optional<Movimiento> buscarMovimiento(int id);

	Movimiento updateMovimiento(Movimiento movimiento);

	void removeMovimiento(int id);
	
	List<Movimiento>buscarMovimientosbyCuenta(Cuenta cuenta);

}