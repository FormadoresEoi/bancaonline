package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.TipoMovimiento;

/**
 * TipoMovimiento repository ====================
 *
 * Repository for the 'TipoMovimientoRepository'.
 *
 * @author Carlos Sánchez <karlos.sangar@gmail.com>
 */

public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Integer> {

}
