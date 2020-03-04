package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

	@Query("select * from clientes where clientes.nombre = :nombre and clientes.pass = :pass ")
	Cliente findByUserPass(
	@Param("usuario") String usuario,
	@Param("pass") String pass);

}