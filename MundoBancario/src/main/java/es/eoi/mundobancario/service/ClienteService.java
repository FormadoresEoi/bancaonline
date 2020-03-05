package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {

	List<Cliente> MostrarCliente();

	Cliente InsertarCliente(Cliente client);

	Optional<Cliente> buscarCliente(int id);

	Cliente updateCliente(Cliente cliente);

	String removeCliente(int id);
	
	public Cliente findByUsuarioAndPass( String usuario,String pass);
	

	

}