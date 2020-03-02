package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	Movimiento FindById(int id);
	
	Movimiento createMovimiento(Movimiento movimiento);
	
	List<Movimiento> listMovimiento();
	
}
