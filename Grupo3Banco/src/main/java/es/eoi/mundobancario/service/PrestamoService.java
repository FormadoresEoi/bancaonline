package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {
	
	//Buscar prestamos por ID
	public Prestamo FindById(int id);

	//Crear Prestamo
	public Prestamo createPrestamo(Prestamo prestamo);

	//Lista de Prestamos
	public List<Prestamo> listPrestamos();

}
