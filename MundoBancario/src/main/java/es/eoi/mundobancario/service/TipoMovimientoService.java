package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.TipoMovimiento;

public interface TipoMovimientoService {

	public TipoMovimiento getById(Integer id);

	public TipoMovimiento getByTipo(String tipo);
}
