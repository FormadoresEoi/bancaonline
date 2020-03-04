package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Prestamo service.
 * =================
 *
 * Service for the Prestamo repository.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {
    private final PrestamoRepository  repository;
    private final CuentaService       cuentaService;
    private final AmortizacionService amortizacionService;

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

    /**
     * @inheritDoc
     */
    @Override
    public List<Prestamo> findAllByCuentaId(String id) {
        return repository.findAllByCuentasNumCuenta(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Prestamo> findAllByCuentaIdVivos(String id) {
        return this.findAllByCuentaId(id)
                   .stream()
                   .filter(p -> p.getAmortizacionesById().size() < p.getPlazos())
                   .collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Prestamo> findAllByCuentaIdAmortizados(String id) {
        return this.findAllByCuentaId(id)
                   .stream()
                   .filter(p -> p.getAmortizacionesById().size() >= p.getPlazos())
                   .collect(Collectors.toList());
    }

    /**
     * Funcionalidad encargada de ejecutar las amortizaciones en caso de cuya fecha coincida
     * con la del sistema, el funcionamiento se explica en detalle en la parte superior.
     */
    @Override
    public void ejecutarAmortizacionesDiarias() {
        if (LocalDateTime.from(Instant.now()).getDayOfMonth() != 1) {
            return;
        }

        find().stream()
              .filter(p -> p.getAmortizacionesById().size() < p.getPlazos())
              .forEach(this::amortizar);
    }

    private void amortizar(Prestamo prestamo) {
        LocalDateTime d = prestamo.getFecha().toLocalDateTime();
        LocalDateTime n = LocalDateTime.from(Instant.now());
        if (d.getMonth() == n.getMonth() && d.getYear() == n.getYear()) {
            return;
        }

        double importe = prestamo.getImporte() / prestamo.getPlazos();

        Cuenta cuenta = prestamo.getCuentasByCuentasNumCuenta();
        cuenta.setSaldo(cuenta.getSaldo() - importe);
        cuentaService.update(cuenta);

        Amortizacion amortizacion = new Amortizacion();
        amortizacion.setImporte(importe);
        amortizacion.setPrestamosId(prestamo.getId());

        amortizacionService.update(amortizacion);
    }
}
