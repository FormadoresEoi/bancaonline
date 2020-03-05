package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Prestamo;

@Repository
public interface AmortizacionRepository extends JpaRepository<Amortizacion, Integer> {

	List<Amortizacion> findAllByPrestamo(Prestamo prestamo);
}
