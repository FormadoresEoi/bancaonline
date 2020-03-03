package es.eoi.mundobancario.repository;

import es.eoi.mundobancario.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    /**
     * Returns all movimientos of the given cuenta.
     *
     * @param numCuenta Cuenta id.
     *
     * @return Cuenta's movimientos.
     */
    List<Movimiento> findAllByCuentasNumCuenta(String numCuenta);
}
