package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cuenta;


public interface CuentaService {
	
	void create(Cuenta dto);

	Optional<Cuenta> find(int id);

	List<Cuenta> findAll();

	void update(Cuenta dto);
	
	List<Cuenta> findBySaldoLessThan(double saldo);

}
