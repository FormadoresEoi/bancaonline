package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.PrestamoRepository;
import es.eoi.mundobancario.entity.Prestamo;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	PrestamoRepository prestamoRepository;

	public Prestamo CreatePrestamo(Prestamo prestamo) {
		return prestamoRepository.save(prestamo);
	}

	public Optional<Prestamo> findPrestamoById(int id) {
		return prestamoRepository.findById(id);
	}

	public Prestamo updatePrestamo(Prestamo prestamo) {
		return prestamoRepository.save(prestamo);
	}

	public void removePrestamo(int id) {
		prestamoRepository.deleteById(id);
	}
}
