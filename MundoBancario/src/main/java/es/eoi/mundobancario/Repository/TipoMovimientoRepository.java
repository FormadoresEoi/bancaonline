package es.eoi.mundobancario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.TipoMovimiento;

@Repository
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Integer> {

	Optional<TipoMovimiento> findByTipo(String string);


}
