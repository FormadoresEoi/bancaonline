package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {

	Prestamo Create(Prestamo prestamo);

	Optional<Prestamo> findById(int id);

	Prestamo update(Prestamo prestamo);

	void remove(int id);
}
