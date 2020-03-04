package es.eoi.mundobancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository repository;

	@Override
	public Cliente getById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public Cliente getByUsuarioAndPass(String usuario, String pass) {
		return repository.findByUsuarioAndPass(usuario, pass).get();
	}

	@Override
	public List<Cliente> getAll() {
		return repository.findAll();
	}

	@Override
	public boolean putEmail(Integer id, String email) {
		if (!repository.existsById(id))
			return false;
		Cliente cliente = repository.findById(id).get();
		cliente.setEmail(email);
		repository.save(cliente);
		return true;
	}

	@Override
	public boolean post(Cliente cliente) {
		if (repository.existsByUsuario(cliente.getUsuario()))
			return false;
		repository.save(cliente);
		return true;
	}

}
