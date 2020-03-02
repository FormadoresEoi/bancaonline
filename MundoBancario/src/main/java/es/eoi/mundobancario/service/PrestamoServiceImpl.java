package es.eoi.mundobancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.PrestamoRepository;
@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository repository;
	
	@Override
	public Prestamo FindById(int id) {
		return repository.findById(id).get();
	}

	@Override
	public Prestamo createPrestamo(Prestamo prestamo) {
		return repository.save(prestamo);
	}

	@Override
	public List<Prestamo> listPrestamos() {
		return repository.findAll();
	}
	
	
	

}
