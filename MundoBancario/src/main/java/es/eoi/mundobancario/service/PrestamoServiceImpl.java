package es.eoi.mundobancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository repository;

	@Override
	public Prestamo getById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public boolean post(Prestamo prestamo) {
		repository.save(prestamo);
		return true;
	}

	@Override
	public List<Prestamo> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Prestamo> getByCuenta(Cuenta cuenta) {
		return repository.findByCuenta(cuenta);
	}

	@Override
	public List<Prestamo> getPrestamosVivos() {
		return repository.FindByPrestamoVivo();
	}

}
