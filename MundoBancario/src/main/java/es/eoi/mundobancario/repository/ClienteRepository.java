package es.eoi.mundobancario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	public Optional<Cliente> findByUsuarioAndPass(String usuario, String pass);

	public boolean existsByUsuario(String usuario);

}
