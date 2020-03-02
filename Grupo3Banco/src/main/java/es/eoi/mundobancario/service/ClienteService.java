package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {
	
	
	//Encontrar todos los Clientes
	
	List<Cliente> findAll();
	
	//Encontrar Cliente por ID
	
	Cliente findById (int id);
	
	//Crear Cliente
	
	void createClient(Cliente cliente);
	
	//Update Cliente
	
	void updateClient(Cliente cliente);
}
