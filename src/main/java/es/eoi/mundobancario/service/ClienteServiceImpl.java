package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.ClienteRepository;

/**
 * Cliente service ===============
 * 
 * Service for the Cliente repository.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	/**
	 * @inheritDoc
	 */
	@Override
	public Optional<Cliente> find(Integer id) {
		return repository.findById(id);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Cliente> find() {
		return repository.findAll();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Cliente update(Cliente entity) {
		return repository.save(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(Cliente entity) {
		repository.delete(entity);
	}
	/**
	 * @inheritDoc
	 */
	@Override
	public Cliente create(Cliente entity) {
		return repository.save(entity);
	}

}
