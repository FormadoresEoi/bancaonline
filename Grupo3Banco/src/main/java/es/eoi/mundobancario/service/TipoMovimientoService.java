package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.TiposMovimiento;

public interface TipoMovimientoService {
	
	//Buscar TipoMovimiento por ID
	
	public TiposMovimiento FindById(int id);
}