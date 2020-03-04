package es.eoi.mundobancario.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.TipoMovimiento;

public interface MovimientoService {

	void create(Movimiento movimiento);

	Optional<Movimiento> findById(int id);

	List<Movimiento> findAll();
	
	List<Movimiento> findAllByCuenta(int id_cuenta);

	void RealizarMovimiento(double importe, 
							Cuenta cuenta, 
							Timestamp fecha, 
							String descripcion, 
							TipoMovimiento.Tipos tipo);
}
