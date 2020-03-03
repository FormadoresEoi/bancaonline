package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.ClientesRepository;
import es.eoi.mundobancario.entity.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	ClientesRepository clientesrepository;

	public Cliente CreateCliente(Cliente cliente) {
		return clientesrepository.save(cliente);
	}

	public Optional<Cliente> findClienteById(int id) {
		return clientesrepository.findById(id);
	}

	public Cliente updateCliente(Cliente cliente) {
		return clientesrepository.save(cliente);
	}

	public void removeCliente(int id) {
		clientesrepository.deleteById(id);
	}
}
