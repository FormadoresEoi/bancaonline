package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.Movimiento;

import java.util.Arrays;
import java.util.List;

/**
 * Movimiento service.
 * ===================
 *
 * Service for the Movimiento repository.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface MovimientoService extends IService<Movimiento, Integer> {
    /**
     * Finds all movimientos by its CuentaId.
     *
     * @param id Cuenta ID.
     *
     * @return Movimientos with given cuentaId.
     */
    List<Movimiento> findByCuentaId(String id);
}
