package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.TipoMovimiento;

public interface TipoMovimientoService {

	Optional<TipoMovimiento> findById(int id);

}
