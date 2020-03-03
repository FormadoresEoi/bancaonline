package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.ClientesRepository;
import es.eoi.mundobancario.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	ClientesRepository clientesRepository;

	public Cliente CreateCliente(Cliente cliente) {
		return clientesRepository.save(cliente);
	}

	public Optional<Cliente> findClienteById(int id) {
		return clientesRepository.findById(id);
	}

	public Cliente updateCliente(Cliente cliente) {
		return clientesRepository.save(cliente);
	}

	public void removeCliente(int id) {
		clientesRepository.deleteById(id);
	}
}
