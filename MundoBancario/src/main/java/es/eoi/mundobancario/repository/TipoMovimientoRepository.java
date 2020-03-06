package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.TiposMovimiento;

public interface TipoMovimientoRepository extends JpaRepository<TiposMovimiento, Integer> {

	TiposMovimiento findByTipo(String tipo);
}
