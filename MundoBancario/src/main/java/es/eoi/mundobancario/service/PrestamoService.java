package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {

	Prestamo FindById(int id);
	
	Prestamo createPrestamo(Prestamo prestamo);
	
	List<Prestamo> listPrestamos();
	
	List<Prestamo> FindByCuenta(int cuenta);
	
	Prestamo FindByPrestamoVivo();
	
}
