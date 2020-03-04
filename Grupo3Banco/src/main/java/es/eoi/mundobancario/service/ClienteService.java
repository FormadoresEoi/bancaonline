package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {
	
	
	//Encontrar todos los Clientes
	
	public List<Cliente> findAll();
	
	//Encontrar Cliente por ID
	
	public Cliente findById (int id);
	
	//Crear Cliente
	
	public void createClient(Cliente cliente);
	
	//Update Cliente
	
	public void updateClient(Cliente cliente, String email);
}
