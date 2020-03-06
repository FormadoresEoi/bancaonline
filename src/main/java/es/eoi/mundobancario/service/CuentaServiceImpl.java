package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;

/**
 * Cliente service ===============
 *
 * Service for the Cliente repository.
 *
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */
@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository  repository;
    private final MovimientoService movimientoService;

    /**
     * @inheritDoc
     */
    @Override
    public Optional<Cuenta> find(String id) {
        return repository.findById(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Cuenta> find() {
        return repository.findAll();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Cuenta update(Cuenta entity) {
        return repository.save(entity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void delete(Cuenta entity) {
        repository.delete(entity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Cuenta> findDeudoras() {
        return repository.findAllBySaldoLessThan(0);
    }

    /**
     * Adds a movimiento to the cuenta.
     *
     * @param id         Cuenta Id.
     * @param movimiento Movimiento to add.
     *
     * @return Created movimiento.
     */
    @Override
    public Movimiento movimiento(String id, Movimiento movimiento) {
        Cuenta     cuenta = find(id).orElseThrow(RuntimeException::new);
        Movimiento mvm    = movimientoService.update(movimiento);

        cuenta.setSaldo(cuenta.getSaldo() + mvm.getImporte());

        update(cuenta);

        return mvm;
    }


    /**
     * Finds and returns all cuentas from a cliente.
     *
     * @param id Cliente ID.
     *
     * @return All cuentas of Cliente.
     */
    public List<Cuenta> findAllByClientesId(int id) {
        return repository.findAllByClientesId(id);
    }

	@Override
	public Cuenta create(Cuenta entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
