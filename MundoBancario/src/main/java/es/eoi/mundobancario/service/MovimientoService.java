package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	Movimiento FindById(int id);
	
	Movimiento createMovimiento(Movimiento movimiento);
	
	Movimiento createIngreso(float importe, int plazos);
	
	List<Movimiento> listMovimiento();
	
	List<Movimiento> findByCuenta(int cuenta); 
}
