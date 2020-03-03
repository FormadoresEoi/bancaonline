package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.CuentaRepository;

/**
 * Cliente service ===============
 * 
 * Service for the Cliente repository.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */
@Service
public class CuentaServiceImpl implements CuentaService {
	@Autowired
	private CuentaRepository repository;

	/**
	 * @inheritDoc
	 */
	@Override
	public Optional<Cuenta> find(Integer id) {
		return repository.findById(id);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Cuenta> find() {
		return repository.findAll();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Cuenta update(Cuenta entity) {
		return repository.save(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void delete(Cuenta entity) {
		repository.delete(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Cuenta> findDeudoras() {
		return repository.findAllBySaldoLessThan(0);
	}
}
