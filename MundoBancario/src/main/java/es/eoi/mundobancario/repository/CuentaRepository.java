package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
	@Query("SELECT cu FROM Cuenta cu  WHERE cu.cliente = :cliente")
	public List<Cuenta> findAllByCliente(@Param("cliente") Cliente cliente);

	public List<Cuenta> findAllBySaldoLessThan(float zero);

}