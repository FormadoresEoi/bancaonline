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

	@Query(value = "SELECT DISTINCT p FROM prestamos AS p INNER JOIN amortizaciones AS a ON p.id = a.prestamo WHERE a.fecha > CURRENT_DATE() AND p.cuenta.id = :idcuenta") 
	List<Prestamo> findByPrestamoVivo(int idcuenta);
	//@Query(value = "SELECT DISTINCT p FROM prestamos WHERE p.plazos == (SELECT COUNT(*) FROM amortizaciones AS a INNER JOIN prestamos ON p.id = a.prestamo WHERE a.fecha > CURRENT_DATE() AND p.cuenta.id = :idcuenta)")
	//List<Prestamo> findByPrestamoAmortizado(int idcuenta);


}
