package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {

	List<Prestamo> MostrarPrestamo();

	Prestamo CrearPrestamo(Prestamo prestamo);

	Optional<Prestamo> buscarPrestamo(int id);

	Prestamo updatePrestamo(Prestamo prestamo);

	void removePrestamo(int id);

	List<Prestamo> buscarPrestamosbyCuenta(Cuenta cuenta);

	List<Prestamo> buscarPrestamosVivos(List<Prestamo> presdto);

	List<Prestamo> buscarPrestamosAmortizados(List<Prestamo> presdto);
}