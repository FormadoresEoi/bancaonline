package es.eoi.mundobancario.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

@Repository
public interface AmortizacionRepository extends JpaRepository<Amortizacion, Integer> {

	List<Amortizacion> findAllByPrestamo(Prestamo prestamo);

	
	//No funciona porque no  puedo sacar la cuenta, sino buscar poor prestamo y de este sacar la cuenta	
	List<Amortizacion> findAllByCuenta(Cuenta cuenta);
	
	
}
