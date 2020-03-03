package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.Cuenta;

import java.util.List;

/**
 * Cuenta service
 * ===============
 * 
 * Service for the Cuenta repository.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */
public interface CuentaService extends IService<Cuenta, Integer> {
    /**
     * Find and returns all Cuentas with negative balance.
     *
     * @return Negative balanaced Cuentas.
     */
    List<Cuenta> findDeudoras();
    
    List<Cuenta> findCuentasCliente();
}
