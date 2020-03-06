package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Movimiento service.
 * ===================
 *
 * Service for the Movimiento repository.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    private MovimientoRepository repository;

    /**
     * @inheritDoc
     */
    @Override
    public Optional<Movimiento> find(Integer id) {
        return repository.findById(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Movimiento> find() {
        return repository.findAll();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Movimiento update(Movimiento entity) {
        return repository.save(entity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void delete(Movimiento entity) {
        repository.delete(entity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Movimiento> findByCuentaId(String id) {
        return repository.findAllByCuentasNumCuenta(id);
    }

	@Override
	public Movimiento create(Movimiento entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
