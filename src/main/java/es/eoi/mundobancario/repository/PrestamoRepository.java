package es.eoi.mundobancario.repository;

import es.eoi.mundobancario.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Prestamo repository.
 * ====================
 *
 * Repository for the Prestamo entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
