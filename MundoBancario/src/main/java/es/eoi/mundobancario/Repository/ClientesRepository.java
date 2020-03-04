package es.eoi.mundobancario.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

//	@Transactional
//	@Query("select email from Cliente u where u.email = ?1")
//	Cliente findByEmailAddress(String emailAddress);

	@Query("select * from Clientes where cliente.usuario = :usuario and cliente.pass = :pass ")
	Cliente findByUserandPass(String user, String pass);

//	@Transactional
//	@Modifying
//	@Query("update Cliente   set email= where Cliente  )")
//	int myUpdateEmail();

}