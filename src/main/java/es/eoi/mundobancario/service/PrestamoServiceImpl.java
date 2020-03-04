package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.*;
import es.eoi.mundobancario.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
    private final MovimientoService   movimientoService;

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
        return repository.saveAndFlush(entity);
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
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) != 1) {
            return;
        }

        find().forEach(this::filtrarAmortizaciones);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Prestamo create(Prestamo prestamo) {
        prestamo = update(prestamo);
        ingresa(
                prestamo.getImporte(),
                cuentaService.find(prestamo.getCuentasNumCuenta()).orElseThrow(RuntimeException::new)
        );

        double             importe        = prestamo.getImporte() / prestamo.getPlazos();
        List<Amortizacion> amortizaciones = new ArrayList<>(prestamo.getPlazos());
        for (int i = 0; i < prestamo.getPlazos(); i++) {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.MONTH, i + 1);

            Amortizacion amortizacion = new Amortizacion();
            amortizacion.setPrestamosId(prestamo.getId());
            amortizacion.setImporte(importe);
            amortizacion.setFecha(now.getTime());

            amortizaciones.add(amortizacionService.update(amortizacion));
        }

        createMovimiento(
                prestamo.getImporte(),
                prestamo.getCuentasNumCuenta(),
                TipoMovimiento.Tipo.PRESTAMO,
                prestamo.getDescripcion()
        );
        prestamo.setAmortizacionesById(amortizaciones);

        return prestamo;
    }

    private void filtrarAmortizaciones(Prestamo prestamo) {
        Calendar now = Calendar.getInstance();
        prestamo.getAmortizacionesById()
                .stream()
                .filter(a -> a.getFecha().after(now.getTime()))
                .findFirst()
                .ifPresent(a -> amortizar(prestamo, a));
    }

    private void amortizar(Prestamo prestamo, Amortizacion a) {
        ingresa(-(prestamo.getImporte() * 1.02), prestamo.getCuentasByCuentasNumCuenta());
        createMovimiento(
                -a.getImporte(),
                prestamo.getCuentasNumCuenta(),
                TipoMovimiento.Tipo.AMORTIZACION,
                prestamo.getDescripcion()
        );
        createMovimiento(
                -(a.getImporte() * 0.02),
                prestamo.getCuentasNumCuenta(),
                TipoMovimiento.Tipo.INTERES,
                prestamo.getDescripcion()
        );
    }

    private void ingresa(double importe, Cuenta cuenta) {
        cuenta.setSaldo(cuenta.getSaldo() + importe);

        cuentaService.update(cuenta);
    }

    private void createMovimiento(
            double importe, String cuentasNumCuenta, TipoMovimiento.Tipo tipo, String descripcion
    ) {
        Movimiento movimiento = new Movimiento();
        movimiento.setImporte(importe);
        movimiento.setMovimientosId(tipo.ordinal());
        movimiento.setCuentasNumCuenta(cuentasNumCuenta);
        movimiento.setDescripcion(descripcion);

        movimientoService.update(movimiento);
    }
}
