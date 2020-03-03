package es.eoi.mundobancario.Service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {
	
	public List<Prestamo> findAllVivos();
	
	public List<Prestamo> findAllAmortizados();

	public Prestamo createPrestamo(Prestamo prestamo);

	public Optional<Prestamo> findPrestamoById(int id);

	public Prestamo updatePrestamo(Prestamo prestamo);

	public void removePrestamo(int id);
}
