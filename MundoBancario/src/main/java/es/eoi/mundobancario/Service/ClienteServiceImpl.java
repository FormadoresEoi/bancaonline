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
	
	private Cliente checkNull(Optional<Cliente> cliente) {
		try {
			if (cliente.isPresent()) {
				return cliente.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Cliente Create(String usuario, String nombre, String pass, String email) {
		return clientesRepository.save(new Cliente(usuario, pass, nombre, email));
	}

	public Cliente findById(int id) {
		return checkNull(clientesRepository.findById(id));
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

	public Cliente login(String user, String pass) {
		return checkNull(clientesRepository.findOneByUsuarioAndPass(user, pass));
	}

	public Cliente findCuentas(int id) {
		return checkNull(clientesRepository.findById(id));
	}
}
