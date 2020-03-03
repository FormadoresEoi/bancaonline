package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository presrepo;

	
	@Override
	public List<Prestamo> MostrarPrestamo() {
		return presrepo.findAll();
	}

	@Override
	public Prestamo CrearPrestamo(Prestamo prestamo) {
		return presrepo.save(prestamo);
	}

	@Override
	public Optional<Prestamo> buscarPrestamo(int id) {
		return presrepo.findById(id);
	}

	@Override
	public Prestamo updatePrestamo(Prestamo prestamo) {
		return presrepo.save(prestamo);
	}

	@Override
	public void removePrestamo(int id) {
		presrepo.deleteById(id);
		
	}

}