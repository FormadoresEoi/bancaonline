package es.eoi.mundobancario.controller;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
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
public class CuentaController implements IController<CuentaDto, String> {
    private final CuentaService     cuentaService;
    private final MovimientoService movimientoService;
    private final PrestamoService   prestamoService;
    private final ModelMapper       mapper;

    /**
     * Devuelve un listado de las cuentas con saldo negativo (Toda la
     * información y datos del cliente).
     */
    @GetMapping("/deudoras")
    public List<CuentaDto> deudoras() {
        return cuentaService.findDeudoras()
                            .stream()
                            .map(c -> mapper.map(c, CuentaDto.class))
                            .collect(Collectors.toList());
    }

    /**
     * Devuelve los movimientos de la cuenta solicitada.
     * (incluirá el tipo de movimiento)
     */
    @GetMapping("/{id}/movimientos")
    public List<MovimientoDto> movimientos(@PathVariable String id) {
        return movimientoService.findByCuentaId(id)
                                .stream()
                                .map(c -> mapper.map(c, MovimientoDto.class))
                                .collect(Collectors.toList());
    }

    /**
     * Devuelve los préstamos de la cuenta.
     * (incluirán las amortizaciones planificadas)
     */
    @GetMapping("/{id}/prestamos")
    public List<PrestamoDto> prestamos(@PathVariable String id) {
        return prestamoService.findAllByCuentaId(id)
                              .stream()
                              .map(c -> mapper.map(c, PrestamoDto.class))
                              .collect(Collectors.toList());
    }

    /**
     * Devuelve los préstamos vivos de la cuenta.
     * (incluirán las amortizaciones planificadas)
     */
    @GetMapping("/{id}/prestamosVivos")
    public List<PrestamoDto> prestamosVivos(@PathVariable String id) {
        return prestamoService.findAllByCuentaIdVivos(id)
                              .stream()
                              .map(c -> mapper.map(c, PrestamoDto.class))
                              .collect(Collectors.toList());
    }

    /**
     * Devuelve los préstamos amortizados de la cuenta.
     * (incluirán las amortizaciones planificadas)
     */
    @GetMapping("/{id}/prestamosAmortizados")
    public List<PrestamoDto> prestamosAmortizados(@PathVariable String id) {
        return prestamoService.findAllByCuentaIdAmortizados(id)
                              .stream()
                              .map(c -> mapper.map(c, PrestamoDto.class))
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
    public PrestamoDto prestamo(@PathVariable String id, @RequestBody PrestamoDto entity) {
        Prestamo prestamo = mapper.map(entity, Prestamo.class);
        prestamo.setCuentasNumCuenta(id);

        return mapper.map(
                prestamoService.update(prestamo),
                PrestamoDto.class
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
    public MovimientoDto movimiento(@PathVariable String id, @RequestBody MovimientoDto entity) {
        Movimiento movimiento = mapper.map(entity, Movimiento.class);
        movimiento.setCuentasNumCuenta(id);

        return mapper.map(
                cuentaService.movimiento(id, movimiento),
                MovimientoDto.class
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    @GetMapping
    public List<CuentaDto> findAll() {
        return cuentaService.find()
                            .stream()
                            .map(c -> mapper.map(c, CuentaDto.class))
                            .collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    @GetMapping("/{id}")
    public CuentaDto findById(@PathVariable String id) {
        return mapper.map(
                cuentaService.find(id)
                             .orElseThrow(RuntimeException::new),
                CuentaDto.class
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    @PostMapping
    public CuentaDto create(@RequestBody CuentaDto entity) {
        return mapper.map(
                cuentaService.update(mapper.map(entity, Cuenta.class)),
                CuentaDto.class
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    @PutMapping
    public CuentaDto update(@RequestBody CuentaDto entity) {
        return mapper.map(
                cuentaService.update(mapper.map(entity, Cuenta.class)),
                CuentaDto.class
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    @DeleteMapping
    public CuentaDto delete(@RequestBody CuentaDto entity) {
        cuentaService.delete(mapper.map(entity, Cuenta.class));

        return entity;
    }
}
