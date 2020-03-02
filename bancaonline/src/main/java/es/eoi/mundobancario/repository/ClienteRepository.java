package es.eoi.mundobancario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findClienteByUsuarioAndPass(String usuario, String pass);
	
	List<Cuenta> findAllCuentasByIdCliente(int IdCliente);
}
