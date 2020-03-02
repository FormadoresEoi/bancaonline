package es.eoi.mundobancario.service;

import java.util.Optional;

import es.eoi.mundobancario.entity.TipoMovimiento;


public interface TipoMovimientoService {

	Optional<TipoMovimiento> findById(int id);
}
