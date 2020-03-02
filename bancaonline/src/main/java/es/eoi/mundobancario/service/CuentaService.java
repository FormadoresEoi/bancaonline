package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cuenta;


public interface CuentaService {
	
	void create(Cuenta dto);

	Optional<Cuenta> find(String id);

	List<Cuenta> findAll();

	void update(Cuenta cuenta);

	void remove(int id);
}
