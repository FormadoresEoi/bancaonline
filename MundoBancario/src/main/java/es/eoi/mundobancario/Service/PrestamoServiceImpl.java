package es.eoi.mundobancario.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.PrestamoRepository;
import es.eoi.mundobancario.entity.Prestamo;

@Service
public class PrestamoServiceImpl implements PrestamoService {
	@Autowired
	PrestamoRepository prestamoRepository;

	public Prestamo createPrestamo(Prestamo prestamo) {
		return prestamoRepository.save(prestamo);
	}

	public Optional<Prestamo> findById(int id) {
		return prestamoRepository.findById(id);
	}

	public Prestamo update(Prestamo prestamo) {
		return prestamoRepository.save(prestamo);
	}

	public void remove(int id) {
		prestamoRepository.deleteById(id);
	}

	public List<Prestamo> findAllVivos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Prestamo> findAllAmortizados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Prestamo> findPrestamoById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prestamo updatePrestamo(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePrestamo(int id) {
		// TODO Auto-generated method stub

	}
}
