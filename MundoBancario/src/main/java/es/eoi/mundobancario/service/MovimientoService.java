package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TiposMovimiento;

public interface MovimientoService {

	List<Movimiento> MostrarMovimiento();

	Movimiento CrearMovimiento(Movimiento movimiento);

	Optional<Movimiento> buscarMovimiento(int id);

	Movimiento updateMovimiento(Movimiento movimiento);

	void removeMovimiento(int id);
	
	List<Movimiento>buscarMovimientosbyCuenta(Cuenta cuenta);

	Movimiento crearMovimientoAmortizacion(Amortizacion amortizacion, Cuenta cuenta,
			TiposMovimiento tipoMovimientos, String descripcion);

	Movimiento crearMovimientoPrestamo(Prestamo prestamo, Cuenta cuenta, TiposMovimiento tipoMovimientos,
			String descripcion);

}