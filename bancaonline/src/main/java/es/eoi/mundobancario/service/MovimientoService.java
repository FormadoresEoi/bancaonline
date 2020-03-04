package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	void create(Movimiento movimiento);

	Optional<Movimiento> findById(int id);

	List<Movimiento> findAll();
	
	List<Movimiento> findAllByCuentaId(int id_cuenta);

}
