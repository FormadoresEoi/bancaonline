package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	

	@Override
	public Optional<Cliente> find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> find() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public Cliente update(Cliente entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cliente entity) {
		// TODO Auto-generated method stub

	}

}
