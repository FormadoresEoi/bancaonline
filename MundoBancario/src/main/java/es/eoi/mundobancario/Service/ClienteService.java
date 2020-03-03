package es.eoi.mundobancario.Service;

import java.util.List;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {

	public Cliente Create(Cliente cliente);

	public Cliente findById(int id);

	public List<Cliente> findAll();

	public Cliente update(Cliente cliente);

	public void remove(int id);

	public Cliente login(int id);

	public Cliente findCuentas(int id);

	public Cliente updateEmail(int id);

	public Cliente findPrestamos(int id);
}
