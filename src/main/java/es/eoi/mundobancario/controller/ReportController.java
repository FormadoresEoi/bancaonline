package es.eoi.mundobancario.controller;

import es.eoi.mundobancario.dto.FullClienteDto;
import es.eoi.mundobancario.dto.FullClienteFullCuentaDto;
import es.eoi.mundobancario.dto.FullPrestamoDto;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Report controller.
 * ==================
 *
 * Controller for the Report entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController implements IController<Void, Void> {
    private final ClienteService clienteService;
    private final PrestamoService prestamoService;
    private final ModelMapper     mapper;

    /**
     * Devuelve los datos del cliente junto al listado de las cuentas de las que
     * dispone y sus movs
     */
    @GetMapping("/clientes/{id}")
    public FullClienteFullCuentaDto cliente(@PathVariable int id) {
        return mapper.map(clienteService.find(id), FullClienteFullCuentaDto.class);
    }

    /**
     * Imprimirá un PDF con los datos del servicio anterior: EOI_BANK_CLIENTE_000.pdf en una
     * ruta estática, los importes de los movimientos de tipo ingreso o préstamo aparecerán de
     * color verde, mientras que el
     * resto de importes se mostrarán en rojo y en negativo, se
     * valorará que tenga una apariencia mínimamente cuidada.
     */
    @PostMapping("/clientes/{id}")
    public void clientePdf(@PathVariable int id) {
        // TODO
    }

    /**
     * Devuelve los datos del cliente junto a la información del préstamo indicado y las
     * amortizaciones planificadas.
     */
    @GetMapping("/presetamos/{id}")
    public FullClienteFullCuentaDto prestamo(@PathVariable int id) {
        return mapper.map(clienteService.find(id), FullClienteFullCuentaDto.class);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Void> findAll() {
        throw new RuntimeException("Not implemented!");
    }

    /**
     * @inheritDoc
     */
    @Override
    public Void findById(Void id) {
        throw new RuntimeException("Not implemented!");
    }

    /**
     * @inheritDoc
     */
    @Override
    public Void create(Void entity) {
        throw new RuntimeException("Not implemented!");
    }

    /**
     * @inheritDoc
     */
    @Override
    public Void update(Void id, Void entity) {
        throw new RuntimeException("Not implemented!");
    }
}
