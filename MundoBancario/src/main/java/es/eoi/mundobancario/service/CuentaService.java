package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cuenta;

public interface CuentaService {

	Cuenta FindById(int id);
	
	Cuenta createCuenta(Cuenta cuenta);
	
	void deleteCuenta(Cuenta cuenta);
	
	Cuenta updateCuenta(Cuenta cuenta);
	
	List<Cuenta> listCuentas();
	
}
