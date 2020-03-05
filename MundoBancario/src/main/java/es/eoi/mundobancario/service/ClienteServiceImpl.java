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
	ClienteRepository clientrepo;

	@Override
	public List<Cliente> MostrarCliente() {
		return clientrepo.findAll();
	}

	@Override
	public Cliente InsertarCliente(Cliente client) {
		return clientrepo.save(client);
	}

	@Override
	public Optional<Cliente> buscarCliente(int id) {

		return clientrepo.findById(id);

	}

	@Override
	public Cliente updateCliente(Cliente cliente) {
		return clientrepo.save(cliente);
	}

	@Override
	public String removeCliente(int id) {
		try {
			clientrepo.deleteById(id);
			return "Cliente borrado";
		} catch (Exception s) {
			s.printStackTrace();
			return "Error borrando el cliente";
		}

	}

	@Override
	public Cliente findByUsuarioAndPass(String usuario, String pass) {

		return clientrepo.findByUsuarioAndPass(usuario, pass);
	}
	

}