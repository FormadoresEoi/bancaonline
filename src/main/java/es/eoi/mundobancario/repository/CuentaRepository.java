package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Cuenta;

/**
 * Cuenta repository ====================
 *
 * Repository for the 'CuentaRepository'.
 *
 * @author Carlos Sánchez <karlos.sangar@gmail.com>
 */

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}
