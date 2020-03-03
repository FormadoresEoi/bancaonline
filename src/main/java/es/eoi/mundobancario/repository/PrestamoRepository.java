package es.eoi.mundobancario.repository;

import es.eoi.mundobancario.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    /**
     * Find and returns all Prestamos by its Cuenta id.
     *
     * @param id Cuenta id.
     *
     * @return Prestamos of given Cuenta.
     */
    List<Prestamo> findAllByCuentasNumCuenta(String id);
}
