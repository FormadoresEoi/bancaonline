package es.eoi.mundobancario.controller;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.service.CuentaService;
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
public class CuentaController implements IController<CuentaDto, Integer> {
    private final CuentaService service;
    private final ModelMapper   mapper;

    /**
     * @inheritDoc
     */
    @Override
    @GetMapping
    public List<CuentaDto> findAll() {
        return service.find()
                      .stream()
                      .map(c -> mapper.map(c, CuentaDto.class))
                      .collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    @GetMapping("/{id}")
    public CuentaDto findById(@PathVariable Integer id) {
        return mapper.map(
                service.find(id)
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
                service.update(mapper.map(entity, Cuenta.class)),
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
                service.update(mapper.map(entity, Cuenta.class)),
                CuentaDto.class
        );
    }

    /**
     * @inheritDoc
     */
    @Override
    @DeleteMapping
    public CuentaDto delete(@RequestBody CuentaDto entity) {
        service.delete(mapper.map(entity, Cuenta.class));

        return entity;
    }
}
