package es.eoi.mundobancario.controller;

import es.eoi.mundobancario.dto.*;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Cuenta controller.
 * ==================
 *
 * Controller for the Cuenta entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/cuenta")
public class CuentaController implements IController<FullCuentaDto, String> {
    private final CuentaService     cuentaService;
    private final MovimientoService movimientoService;
    private final PrestamoService   prestamoService;
    private final ModelMapper       mapper;

    /**
     * Devuelve un listado de las cuentas con saldo negativo (Toda la
     * información y datos del cliente).
     */
    @GetMapping("/deudoras")
    public List<FullCuentaDto> deudoras() {
        return cuentaService.findDeudoras()
                            .stream()
                            .map(c -> mapper.map(c, FullCuentaDto.class))
                            .collect(Collectors.toList());
    }

    /**
     * Devuelve los movimientos de la cuenta solicitada.
     * (incluirá el tipo de movimiento)
     */
    @GetMapping("/{id}/movimientos")
    public List<FullMovimientoDto> movimientos(@PathVariable String id) {
        return movimientoService.findByCuentaId(id)
                                .stream()
                                .map(c -> mapper.map(c, FullMovimientoDto.class))
                                .collect(Collectors.toList());
    }

    /**
     * Devuelve los préstamos de la cuenta.
     * (incluirán las amortizaciones planificadas)
     */
    @GetMapping("/{id}/prestamos")
    public List<FullPrestamoDto> prestamos(@PathVariable String id) {
        return prestamoService.findAllByCuentaId(id)
                              .stream()
                              .map(c -> mapper.map(c, FullPrestamoDto.class))
                              .collect(Collectors.toList());
    }

    /**
     * Devuelve los préstamos vivos de la cuenta.
     * (incluirán las amortizaciones planificadas)
     */
    @GetMapping("/{id}/prestamosVivos")
    public List<FullPrestamoDto> prestamosVivos(@PathVariable String id) {
        return prestamoService.findAllByCuentaIdVivos(id)
                              .stream()
                              .map(c -> mapper.map(c, FullPrestamoDto.class))
                              .collect(Collectors.toList());
    }

    /**
     * Devuelve los préstamos amortizados de la cuenta.
     * (incluirán las amortizaciones planificadas)
     */
    @GetMapping("/{id}/prestamosAmortizados")
    public List<FullPrestamoDto> prestamosAmortizados(@PathVariable String id) {
        return prestamoService.findAllByCuentaIdAmortizados(id)
                              .stream()
                              .map(c -> mapper.map(c, FullPrestamoDto.class))
                              .collect(Collectors.toList());
    }

    /**
     * Crearemos un préstamo nuevo.
     *
     * @param id     Cuenta Id.
     * @param entity Entity to create.
     *
     * @return Created entity.
     */
    @PostMapping("/{id}/prestamos")
    public FullPrestamoDto prestamo(@PathVariable String id, @RequestBody PrestamoDto entity) {
        Prestamo prestamo = mapper.map(entity, Prestamo.class);
        prestamo.setCuentasNumCuenta(id);

        return mapper.map(
                prestamoService.update(prestamo),
                FullPrestamoDto.class
        );
    }

    /**
     * Crearemos un pago/ingreso nuevo.
     *
     * @param id     Cuenta Id.
     * @param entity Entity to create.
     *
     * @return Created entity.
     */
    @PostMapping({
            "/{id}/pagos",
            "/{id}/ingresos"
    })
    public FullMovimientoDto movimiento(@PathVariable String id, @RequestBody MovimientoDto entity) {
        Movimiento movimiento = mapper.map(entity, Movimiento.class);
        movimiento.setCuentasNumCuenta(id);

        return mapper.map(
                cuentaService.movimiento(id, movimiento),
                FullMovimientoDto.class
        );
    }

    /**
     * Funcionalidad encargada de ejecutar las amortizaciones en caso de cuya fecha coincida
     * con la del sistema, el funcionamiento se explica en detalle en la parte superior.
     */
    @PostMapping("/ejecutarAmortizacionesDiarias")
    public void ejecutarAmortizacionesDiarias() {
        prestamoService.ejecutarAmortizacionesDiarias();
    }

    /**
     * @inheritDoc
     */
    @Override
    @GetMapping
    public List<FullCuentaDto> findAll() {
        return cuentaService.find()
                            .stream()
                            .map(c -> mapper.map(c, FullCuentaDto.class))
                            .collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    @GetMapping("/{id}")
    public FullCuentaDto findById(@PathVariable String id) {
        return mapper.map(
                cuentaService.find(id)
                             .orElseThrow(RuntimeException::new),
                FullCuentaDto.class
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    @PostMapping
    public FullCuentaDto create(@RequestBody FullCuentaDto entity) {
        return mapper.map(
                cuentaService.update(mapper.map(entity, Cuenta.class)),
                FullCuentaDto.class
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    @PutMapping("/{id}")
    public FullCuentaDto update(@PathVariable String id, @RequestBody FullCuentaDto entity) {
        Cuenta cuenta = mapper.map(findById(id), Cuenta.class);
        cuenta.setAlias(entity.getAlias());

        return mapper.map(
                cuentaService.update(cuenta),
                FullCuentaDto.class
        );
    }
}
