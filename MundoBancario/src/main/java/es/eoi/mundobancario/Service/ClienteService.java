package es.eoi.mundobancario.Service;

import java.util.List;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {

	public Cliente Create(String usuario, String nombre, String pass, String email);

	public Cliente findById(int id);

	public List<Cliente> findAll();

	public Cliente update(Cliente cliente);

	public void remove(int id);

	public Cliente login(String user, String pass);

	public Cliente findCuentas(int id);

	public Cliente updateEmail(int id);
}
