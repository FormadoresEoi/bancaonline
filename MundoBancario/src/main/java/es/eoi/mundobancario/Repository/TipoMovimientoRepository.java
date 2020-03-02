package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.TipoMovimiento;

public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Integer> {

}
