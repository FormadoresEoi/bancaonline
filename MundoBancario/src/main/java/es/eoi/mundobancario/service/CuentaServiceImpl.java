package es.eoi.mundobancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	CuentaRepository repository;

	@Override
	public Cuenta getById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Cuenta> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Cuenta> getDeudoras() {
		return repository.findByDeudas();
	}

	@Override
	public boolean post(Cuenta cuenta) {
		repository.save(cuenta);
		return true;
	}

	@Override
	public boolean putAlias(Integer id, String alias) {
		Cuenta cuenta = repository.findById(id).get();
		cuenta.setAlias(alias);
		repository.save(cuenta);
		return true;
	}

}
