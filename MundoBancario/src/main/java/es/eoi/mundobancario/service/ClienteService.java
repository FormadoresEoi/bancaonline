package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {

	public Cliente getById(Integer id);

	public Cliente getByUsuarioAndPass(String usuario, String pass);

	public List<Cliente> getAll();

	public boolean post(Cliente cliente);

	public boolean putEmail(Integer id, String email);

}
