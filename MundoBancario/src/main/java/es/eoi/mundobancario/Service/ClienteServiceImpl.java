package es.eoi.mundobancario.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.ClientesRepository;
import es.eoi.mundobancario.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
	@Autowired
	ClientesRepository clientesRepository;

	public Cliente Create(Cliente cliente) {
		return clientesRepository.save(cliente);
	}

	public Optional<Cliente> findById(int id) {
		return clientesRepository.findById(id);
	}

	public List<Cliente> findAll() {
		return clientesRepository.findAll();
	}

	public Cliente update(Cliente cliente) {
		return clientesRepository.save(cliente);
	}

	public void remove(int id) {
		clientesRepository.deleteById(id);
	}

	public Optional<Cliente> findPrestamos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cliente login(int id) {

		return null;
	}

	public Cliente findCuentas(int id) {

		return null;
	}

	@Override
	public Optional<Cliente> updateEmail(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
