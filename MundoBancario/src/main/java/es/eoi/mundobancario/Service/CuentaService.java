package es.eoi.mundobancario.Service;

import java.util.List;

import es.eoi.mundobancario.entity.Cuenta;

public interface CuentaService {

	public Cuenta create(Cuenta cuenta);

	public Cuenta findById(int numCuenta);

	public Cuenta update(Cuenta cuenta);

	public void remove(int numCuenta);

	public void ejecutarAmortizacionsDiarias();

	public Cuenta createPagos(int id);

	public Cuenta createPrestamos(int id);

	public Cuenta createIngresos(int id);

	public Cuenta findPrestamosAmortizados(int id);

	public Cuenta findPrestamosVivos(int id);

	public Cuenta findPresatmos(int id);

	public Cuenta findMovimientos(int id);

	public List<Cuenta> findAllDeudora();

	public List<Cuenta> findAll();
}
