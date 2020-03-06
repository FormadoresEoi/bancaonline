package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.stream.Collectors;

import es.eoi.mundobancario.dto.LoginDto;
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
import es.eoi.mundobancario.dto.FullClienteDto;
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

	/**
	 * Devuelve un listado con todos los clientes sin la contraseña
	 * 
	 */
	@GetMapping
	public List<ClienteDto> findAll() {
		return clienteService.find().stream().map(c -> mapper.map(c, ClienteDto.class))
				.collect(Collectors.toList());

	}

	/**
	 * Devuelve el cliente solicitado por id sin la contraseña.
	 * 
	 */
	@GetMapping("/{id}")
	public ClienteDto findById(@PathVariable Integer id) {
		return mapper.map(
				clienteService.find(id).orElseThrow(RuntimeException::new),
				ClienteDto.class
		);
	}

	/**
	 * Creates an entity.
	 *
	 * @param entity Entity to create.
	 *
	 * @return Created entity.
	 */
	@Override
	public ClienteDto create(ClienteDto entity) {
		return null;
	}

	/**
	 * Devuelve el cliente solicitado.
	 * 
	 */
	@GetMapping("/login")
	public ClienteDto login(@RequestBody LoginDto cliente) {
		return mapper.map(
				clienteService.login(cliente.getUsuario(), cliente.getPass()),
				ClienteDto.class
		);
	}
	
	/**
	 * Modifica un cliente.
	 * 
	 */
	@PutMapping("/{id}")
	public ClienteDto update(@PathVariable Integer id, @RequestBody ClienteDto entity) {
		Cliente cliente = mapper.map(findById(id), Cliente.class);
		cliente.setEmail(entity.getEmail());
		return mapper.map(clienteService.update(cliente), ClienteDto.class);
	}

	/**
	 * Devuelve las cuentas del cliente solicitado.
	 * 
	 */
	@GetMapping("/{id}/cuentas")
	public List<CuentaDto> listCuentas(@PathVariable Integer id) {
		return cuentaService.findAllByClientesId(id).stream().map(c -> mapper.map(c, CuentaDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * Crea un nuevo cliente.
	 * 
	 */
	@PostMapping
	public ClienteDto create(@RequestBody FullClienteDto cliente) {
		return mapper.map(
				clienteService.create(mapper.map(cliente, Cliente.class)),
				ClienteDto.class
		);
	}
}
