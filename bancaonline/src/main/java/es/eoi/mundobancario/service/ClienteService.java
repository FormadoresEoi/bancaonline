package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

public interface ClienteService {
	
	void create(Cliente cliente);

	Optional<Cliente> find(int id);
	
	Optional<Cliente> findByUsuarioAndPass(String usuario, String pass);

	List<Cliente> findAll();
	
//	List<Cuenta> findAllCuentasByIdCliente(int id);

	void updateCliente(Cliente cliente);
}
