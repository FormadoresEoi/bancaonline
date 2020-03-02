package es.eoi.mundobancario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {
	
	public final ClienteRepository repository;
	
	@Override
	public List<Cliente> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Cliente findById(int id) {
		return this.repository.findById(id).get();
	}

	@Override
	public void createClient(Cliente cliente) {
		this.repository.save(cliente);
		
	}

	@Override
	public void updateClient(Cliente cliente) {
		this.repository.save(cliente);
		
		
	}

}
