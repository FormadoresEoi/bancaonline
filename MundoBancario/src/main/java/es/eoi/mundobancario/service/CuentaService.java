package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

public interface CuentaService {

	List<Cuenta> MostrarCuenta();

	Cuenta InsertarCuenta(Cuenta cuenta);

	Optional<Cuenta> buscarCuenta(int num_cuenta);

	Cuenta updateCuenta(Cuenta cuenta);

	void removeCuenta(int id);
	
	List<Cuenta> findAllById_Clientes(Cliente cliente);
	
	List<Cuenta> buscarCuentasDeudoras(float zero);

	void ActualizarSaldoPrestamo(Prestamo prestamo, Cuenta cuenta);

	void ActualizarSaldoAmortizacion(Amortizacion amortizacion, Cuenta cuenta);

}