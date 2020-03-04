package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Cuenta;

import java.util.List;

/**
 * Cuenta repository ====================
 *
 * Repository for the 'CuentaRepository'.
 *
 * @author Carlos Sánchez <karlos.sangar@gmail.com>
 */

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    /**
     * Find and returns all Cuentas balance less than specified.
     *
     * @param max Max balance allowed.
     *
     * @return Filtered balanaced Cuentas.
     */
    List<Cuenta> findAllBySaldoLessThan(double max);
    
    List<Cuenta> findCuentas(String numCuenta);
}
