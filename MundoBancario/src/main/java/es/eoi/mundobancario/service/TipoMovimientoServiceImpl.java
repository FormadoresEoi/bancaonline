package es.eoi.mundobancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;

@Service
public class TipoMovimientoServiceImpl implements TipoMovimientoService {

	@Autowired
	TipoMovimientoRepository repository;

	@Override
	public TipoMovimiento getById(Integer id) {
		return repository.findById(id).get();
	}

	public TipoMovimiento getByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

}
