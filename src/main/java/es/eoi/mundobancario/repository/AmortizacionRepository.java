package es.eoi.mundobancario.repository;

import es.eoi.mundobancario.entity.Amortizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Amortización repository.
 * ========================
 *
 * Repository for the Amortizacion entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Repository
public interface AmortizacionRepository extends JpaRepository<Amortizacion, Integer> {
}
