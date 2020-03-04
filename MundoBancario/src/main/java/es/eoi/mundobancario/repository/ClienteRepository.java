package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.dto.CuentaDTOCreate;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
//	@Query("SELECT cli.listCuentas FROM Cliente cli  WHERE cli.id = :id")
//    public List<Cuenta> findCuentasById(@Param("id") int id);

}
