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
	public Cuenta FindById(int id) {
		return repository.findById(id).get();
	}

	@Override
	public void createCuenta(Cuenta cuenta) {
		repository.save(cuenta);
	}

	@Override
	public void deleteCuenta(Cuenta cuenta) {
		repository.delete(cuenta);
	}

	@Override
	public void updateCuenta(Cuenta cuenta, String alias) {
		cuenta.setAlias(alias);
		this.repository.save(cuenta);
	}

	@Override
	public List<Cuenta> listCuentas() {
		return repository.findAll();
	}
}