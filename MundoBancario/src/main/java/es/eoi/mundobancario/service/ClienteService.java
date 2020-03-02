package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {

	Cliente FindById(int id);
	
	Cliente createCliente(Cliente cliente);
	
	void deleteCliente(Cliente cliente);
	
	Cliente updateCliente(Cliente cliente);
	
	List<Cliente> listClientes();
	
}
