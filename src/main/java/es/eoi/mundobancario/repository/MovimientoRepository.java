package es.eoi.mundobancario.repository;

import es.eoi.mundobancario.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Movimiento repository.
 * ======================
 *
 * Repository for the Movimiento entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
}
