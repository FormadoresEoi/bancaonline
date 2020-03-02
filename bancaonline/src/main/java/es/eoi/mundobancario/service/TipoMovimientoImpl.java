package es.eoi.mundobancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;

@Service
public class TipoMovimientoImpl implements TipoMovimientoService {
	
	
	@Autowired
	private TipoMovimientoRepository repository;
	
	@Override
	public TipoMovimiento findById(int id){
		
		return this.repository.findById(id).get();
		
	}

}
