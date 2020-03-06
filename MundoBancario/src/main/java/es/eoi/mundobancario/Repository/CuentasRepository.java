package es.eoi.mundobancario.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cuenta;

@Repository
public interface CuentasRepository extends JpaRepository<Cuenta, Integer> {

	@Query("SELECT c From Cuenta c WHERE c.saldo < 0")
	public List<Cuenta> findBySaldoLessThan();

}
