package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {
	
	@Autowired
	private CuentaRepository repository;
	
	@Override
	public void create(Cuenta dto) {
		repository.save(dto);
	}

	@Override
	public Optional<Cuenta> find(int numCuenta) {
		return repository.findById(numCuenta);
	}
	
	@Override
	public List<Cuenta> findAll(){
		return repository.findAll();
	}

	@Override
	public void update(Cuenta dto) {
		this.repository.save(dto);
	}
	
}
