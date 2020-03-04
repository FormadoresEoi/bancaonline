package es.eoi.mundobancario.service;

import java.util.Collection;
import java.util.List;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;

/**
 * Cuenta service
 * ===============
 * 
 * Service for the Cuenta repository.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */
public interface CuentaService extends IService<Cuenta, String> {
    /**
     * Find and returns all Cuentas with negative balance.
     *
     * @return Negative balanaced Cuentas.
     */
    List<Cuenta> findDeudoras();

    /**
     * Adds a movimiento to the cuenta.
     *
     * @param id         Cuenta Id.
     * @param movimiento Movimiento to add.
     *
     * @return Created movimiento.
     */
    Movimiento movimiento(String id, Movimiento movimiento);

    /**
     * Finds and returns all cuentas from a cliente.
     *
     * @param id Cliente ID.
     *
     * @return All cuentas of Cliente.
     */
    List<Cuenta> findAllByClientesId(int id);
}
