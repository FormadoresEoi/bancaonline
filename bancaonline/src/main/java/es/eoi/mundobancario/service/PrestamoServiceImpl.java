package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.CuentaRepository;
import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService{

	@Autowired
	PrestamoRepository prestamoRepository;
	
	@Autowired
	CuentaRepository cuentaRepository;
	
	@Override
	public void create(Prestamo prestamo) {
		prestamoRepository.save(prestamo);
	}

	@Override
	public Optional<Prestamo> findById(int id) {
		return prestamoRepository.findById(id);
	}

	@Override
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@Override
	public List<Prestamo> findAllByIdCuenta(int IdCuenta) {
		return prestamoRepository.findAllByIdCuenta(IdCuenta);
	}

}
