package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public Optional<Prestamo> findByCuentaId(int id) {
		return prestamoRepository.findById(id);
	}

	@Override
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@Override
	public List<Prestamo> findAllByCuentaId(int id) {
		return prestamoRepository.findAllById_Cuenta(id);
	}
	
	@Override
	public List<Prestamo> findAllByCuentaIdAmortizados(int id) {
		return this.findAllByCuentaId(id)
				.stream()
				.filter(p -> p.getAmortizaciones().size() >= p.getPlazos())
				.collect(Collectors.toList());
	}

	@Override
	public void ejecutarAmortizacionesDiarias(Prestamo prestamo) {
		
	}


}
