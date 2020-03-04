package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {

	public Prestamo getById(Integer id);

	public List<Prestamo> getAll();

	public List<Prestamo> getByCuenta(Cuenta cuenta);

	public List<Prestamo> getPrestamosVivos();

	public boolean post(Prestamo prestamo);

}
