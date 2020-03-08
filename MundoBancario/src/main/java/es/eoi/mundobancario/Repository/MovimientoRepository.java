package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

}
