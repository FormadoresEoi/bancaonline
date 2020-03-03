package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {
	
	void create(Cliente cliente);

	Optional<Cliente> find(int id);
	
	Optional<Cliente> findByUsuarioAndPass(String usuario, String pass);

	List<Cliente> findAll();
	
	void updateCliente(Cliente cliente);
}
