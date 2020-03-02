package es.eoi.mundobancario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;

@Service
public class TipoMovimientoImpl implements TipoMovimientoService {
	
	@Autowired
	private TipoMovimientoRepository repository;
	
	@Override
	public Optional<TipoMovimiento> findById(int id){
		return repository.findById(id);
		
	}

}
