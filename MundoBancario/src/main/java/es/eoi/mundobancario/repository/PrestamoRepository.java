package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{

	@Query(value = "select p from prestamos p where p.cuenta.id = :cuenta")
	List<Prestamo> FindByCuenta(int cuenta);
	
	@Query(value = "select distinct p from prestamos p\r\n" + 
			" inner join amortizaciones a on p.id = a.prestamo where a.fecha > current_date()")
	Prestamo FindByPrestamoVivo();
	
}