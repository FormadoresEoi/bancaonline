package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService{

	void create(Prestamo prestamo);
	
	Optional<Prestamo> findById(int id);
	
	List<Prestamo> findAll();
	
	//List<Prestamo> findAllByIdCuenta(int IdCuenta);
	
	
}
