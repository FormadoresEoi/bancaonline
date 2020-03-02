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
	public Cliente FindById(int id) {
		return repository.findById(id).get();
	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public void deleteCliente(Cliente cliente) {
		repository.delete(cliente);
		
	}

	@Override
	public Cliente updateCliente(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public List<Cliente> listClientes() {
		return repository.findAll();
	}

}
