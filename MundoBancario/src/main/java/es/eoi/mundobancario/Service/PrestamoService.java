package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {

	Prestamo CreatePrestamo(Prestamo prestamo);

	Optional<Prestamo> findPrestamoById(int id);

	Prestamo updatePrestamo(Prestamo prestamo);

	void removePrestamo(int id);
}
