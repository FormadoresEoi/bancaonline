package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{

}
