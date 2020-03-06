package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.NewPrestamoDTO;
import es.eoi.mundobancario.dto.PrestamoDTO;
import es.eoi.mundobancario.dto.TiposMovimientoDTO;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TiposMovimiento;

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
	
	//Listado de Cuentas Duedoras
	public List<Cuenta> listDeudoras();
	
	//Listado de Movimientos en la cuenta.
	public List<Movimiento> listMovimientos(int id);
	
	//Listado de Prestamos Vivos.
	public List<Prestamo> listPrestamosVivos(int id);
	
	//Listado de Prestamos Amortizados.
	public List<Prestamo> listPrestamosAmortizados(int id);
	
	//Creacion de Prestamo
	void CreatePrestamo(Prestamo prestamo, int id);
	
	//Creacion de Ingreso
	void CreateIngreso();
	
	//Create Pago
	void CreatePago();
	
}