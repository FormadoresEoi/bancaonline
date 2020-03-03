package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
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
	public void removeCliente(int id) {
		clientrepo.deleteById(id);
	}

//	@Override
//	public List<Cuenta> findListCuentasById(int id) {
//
//		return clientrepo.findListCuentasById(id);
//	}
}