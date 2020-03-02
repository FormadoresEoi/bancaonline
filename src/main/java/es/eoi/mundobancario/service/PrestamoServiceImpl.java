package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Prestamo service.
 * =================
 *
 * Service for the Prestamo repository.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
public class PrestamoServiceImpl implements PrestamoService {
    @Autowired
    private PrestamoRepository repository;

    /**
     * @inheritDoc
     */
    @Override
    public Optional<Prestamo> find(Integer id) {
        return repository.findById(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Prestamo> find() {
        return repository.findAll();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Prestamo update(Prestamo entity) {
        return repository.save(entity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void delete(Prestamo entity) {
        repository.delete(entity);
    }
}
