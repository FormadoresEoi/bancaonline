package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;

public interface CuentaService {

	//Buscar cuenta por ID
	
	public Cuenta findById(int id);
	
	//Crear Cuneta
	
	 void createCuenta(Cuenta cuenta);
	
	//Eliminar Cuenta
	
	 void deleteCuenta(Cuenta cuenta);
	
	//Actualizar Cuenta
	
	 void updateCuenta(Cuenta cuenta, String alias);
	
	//Listado de Cuentas
	
	public List<Cuenta> listCuentas();
	
	public List<Cuenta> listDeudoras();
	
	public List<Movimiento> listMovimientos(int id);
	
	public List<Prestamo> listPrestamosVivos(int id);
	
	public List<Prestamo> listPrestamosAmortizados(int id);
	
}