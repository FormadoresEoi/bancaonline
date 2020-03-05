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
	List<Prestamo> findByPrestamoVivoByCuentaId(int idcuenta);

	@Query(value = "SELECT DISTINCT p FROM prestamos AS p INNER JOIN amortizaciones AS a ON p.id = a.prestamo WHERE a.fecha > CURRENT_DATE()")
	List<Prestamo> findByPrestamoVivoAll();
	
	@Query(value = "SELECT s.id, s.descripcion, s.fecha, s.importe, s.plazos, s.cuenta FROM (SELECT p.id, p.descripcion, p.fecha, p.importe, p.plazos, p.cuenta, COUNT(p.id) AS pagados FROM prestamos AS p INNER JOIN amortizaciones AS a ON p.id = prestamo WHERE a.fecha < current_date() GROUP BY a.prestamo) AS s WHERE pagados = plazos AND cuenta = :idcuenta", nativeQuery = true)
	List<Prestamo> findByPrestamoAmortizadoByCuentaId(int idcuenta);
	
	@Query(value = "SELECT s.id, s.descripcion, s.fecha, s.importe, s.plazos, s.cuenta FROM (SELECT p.id, p.descripcion, p.fecha, p.importe, p.plazos, p.cuenta, COUNT(p.id) AS pagados FROM prestamos AS p INNER JOIN amortizaciones AS a ON p.id = prestamo WHERE a.fecha < current_date() GROUP BY a.prestamo) AS s WHERE pagados = plazos", nativeQuery = true)
	List<Prestamo> findByPrestamoAmortizadoAll();


}
