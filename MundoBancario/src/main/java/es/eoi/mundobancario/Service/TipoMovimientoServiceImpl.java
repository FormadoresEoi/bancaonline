package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.TipoMovimientoRepository;
import es.eoi.mundobancario.entity.TipoMovimiento;

@Service
public class TipoMovimientoServiceImpl implements TipoMovimientoService {

	@Autowired
	TipoMovimientoRepository repository;

	public Optional<TipoMovimiento> findById(int id) {
		return repository.findById(id);
	}

}
