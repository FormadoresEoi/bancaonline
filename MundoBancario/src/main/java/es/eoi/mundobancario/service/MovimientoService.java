package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	Movimiento getById(Integer id);

	List<Movimiento> getAll();

	List<Movimiento> getByCuenta(Cuenta cuenta);

	boolean post(Movimiento movimiento);

}
