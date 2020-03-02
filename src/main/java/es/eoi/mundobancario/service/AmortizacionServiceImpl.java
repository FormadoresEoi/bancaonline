package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.repository.AmortizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Amortización service.
 * =====================
 *
 * Service for the Amortizacion repository.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
public class AmortizacionServiceImpl implements AmortizacionService {
    @Autowired
    private AmortizacionRepository repository;

    /**
     * @inheritDoc
     */
    @Override
    public Optional<Amortizacion> find(Integer id) {
        return repository.findById(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Amortizacion> find() {
        return repository.findAll();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Amortizacion update(Amortizacion entity) {
        return repository.save(entity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void delete(Amortizacion entity) {
        repository.delete(entity);
    }
}
