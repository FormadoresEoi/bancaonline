package es.eoi.mundobancario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

	Optional<Prestamo> findByCuenta(int id);
	
	
	List<Prestamo> findAllByCuentaNumCuenta(int id_cuenta);

}
