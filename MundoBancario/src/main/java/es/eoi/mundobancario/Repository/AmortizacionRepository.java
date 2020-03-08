package es.eoi.mundobancario.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Amortizacion;

@Repository
public interface AmortizacionRepository extends JpaRepository<Amortizacion, Integer> {

	List<Amortizacion> findAllByFechaEquals(Date date);

}
