package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

	List<Prestamo> findByCuenta(Cuenta cuenta);

	@Query(value = "select distinct p from prestamos p inner join p.amortizaciones a on p.id = a.prestamo where a.fecha.id > current_date()")
	List<Prestamo> findByPrestamoVivo();

}
