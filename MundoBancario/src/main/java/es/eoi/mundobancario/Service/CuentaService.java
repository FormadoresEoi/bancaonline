package es.eoi.mundobancario.Service;

import java.util.List;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;

public interface CuentaService {

	public Cuenta create(Cuenta cuenta);

	public Cuenta findById(int id);

	public Cuenta update(Cuenta cuenta);

	public void remove(int id);

	public void ejecutarAmortizacionsDiarias();

	public Movimiento createPagos(Movimiento movimiento);

	public Prestamo createPrestamos(Prestamo prestamo, Movimiento movimiento, int id);

	public Movimiento createIngresos(Movimiento movimiento);

	public Cuenta findPrestamosAmortizados(int id);

	public Cuenta findPrestamosVivos(int id);

	public List<Cuenta> findAll();
	
	public List<Cuenta> findAllDeudora();
}
