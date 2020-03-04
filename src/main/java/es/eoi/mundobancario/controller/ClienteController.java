package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import lombok.RequiredArgsConstructor;

/**
 * Cliente controller ===============
 * 
 * Controller for the ClienteDTO.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */

@RequiredArgsConstructor
@RequestMapping("/clientes")
@RestController
public class ClienteController implements IController<ClienteDto, Integer> {
	private final ModelMapper mapper;
	private final ClienteService clienteService;
	private final CuentaService cuentaService;

	@GetMapping
	@Override
	public List<ClienteDto> findAll() {
		return clienteService.find().stream().map(c -> mapper.map(c, ClienteDto.class)).collect(Collectors.toList());

	}

	@GetMapping("/{id}")
	@Override
	public ClienteDto findById(Integer id) {
		return mapper.map(clienteService.find(id).orElseThrow(RuntimeException::new), ClienteDto.class);
	}

//	@GetMapping("/login")
//	public ClienteDto login(@PathVariable int id, String nombre, String email, String usuario) {
//		Cliente cli = mapper.map(findById(id), Cliente.class);
//		return clienteService.showLogin(cli.getId(), cli.getNombre(), cli.getEmail(), cli.getUsuario());
//
//	}

	@PutMapping("/{id}")
	@Override
	public ClienteDto update(@PathVariable Integer id, @RequestBody ClienteDto entity) {
		Cliente cliente = mapper.map(findById(id), Cliente.class);
		return mapper.map(clienteService.update(cliente), ClienteDto.class);
	}

	@GetMapping("/{id}/cuentas")
	public List<CuentaDto> listCuentas(@PathVariable int id) {

		return cuentaService.findAllByClientesId(id).stream().map(c -> mapper.map(c, CuentaDto.class))
				.collect(Collectors.toList());
	}

	@PostMapping()
	@Override
	public ClienteDto create(@RequestBody ClienteDto entity ) {
		return mapper.map(clienteService.create(mapper.map(entity, Cliente.class)), ClienteDto.class);
	}
}
