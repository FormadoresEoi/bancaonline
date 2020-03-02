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
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(dto.getCliente());
		cuenta.setSaldo(dto.getSaldo());
		repository.save(cuenta);
	}

	@Override
	public Optional<Cuenta> find(int id) {
		return repository.findById(Integer.valueOf(id));
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
