package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

public interface ClienteService {

	List<Cliente> MostrarCliente();

	Cliente InsertarCliente(Cliente client);

	Optional<Cliente> buscarCliente(int id);

	Cliente updateCliente(Cliente cliente);

	void removeCliente(int id);
	
//	public List<Cuenta> findListCuentasById(int id);
	

}