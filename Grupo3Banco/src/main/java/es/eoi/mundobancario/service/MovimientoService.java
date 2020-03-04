package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	//Buscar movimiento por ID
	public Movimiento FindById(int id);

	//Crear Movimiento
	public Movimiento createMovimiento(Movimiento movimiento);

	//Lista de movimientos
	public List<Movimiento> listMovimiento();

}