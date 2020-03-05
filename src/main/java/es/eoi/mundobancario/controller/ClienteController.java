package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ClienteController implements IController<FullClienteDto, Integer> {
	private final ModelMapper mapper;
	private final ClienteService clienteService;
	private final CuentaService cuentaService;

	/**
	 * Devuelve un listado con todos los clientes sin la contraseña
	 * 
	 */
	@GetMapping
	public List<FullClienteDto> findAll() {
		return clienteService.find().stream().map(c -> mapper.map(c, FullClienteDto.class))
				.collect(Collectors.toList());

	}

	/**
	 * Devuelve el cliente solicitado por id sin la contraseña.
	 * 
	 */
	@GetMapping("/{id}")
	public FullClienteDto findById(int id) {
		return mapper.map(clienteService.find(id).orElseThrow(RuntimeException::new), FullClienteDto.class);
	}

//	@GetMapping("/login")
//	public ClienteDto login(@PathVariable int id, String nombre, String email, String usuario) {
//		Cliente cli = mapper.map(findById(id), Cliente.class);
//		return clienteService.showLogin(cli.getId(), cli.getNombre(), cli.getEmail(), cli.getUsuario());
//
//	}

//	@PutMapping("/{id}")
//	public FullClienteDto updateEmail(@PathVariable String email, @RequestBody ClienteDto entity) {
//		Cliente cliente = mapper.map(findById(id), Cliente.class);
//		return mapper.map(clienteService.update(entity, cliente.setEmail(email), FullClienteDto.class);
//	}
	
	@GetMapping("/{id}")
	public ClienteDto updateCliente(@PathVariable int id, String email, ClienteDto entity) {
		Cliente cliente = mapper.map(findById(id), Cliente.class);
		cliente.getEmail();
		return mapper.map(clienteService.update(cliente), ClienteDto.class);
	}
	

	@GetMapping("/{id}/cuentas")
	public List<CuentaDto> listCuentas(@PathVariable int id) {

		return cuentaService.findAllByClientesId(id).stream().map(c -> mapper.map(c, CuentaDto.class))
				.collect(Collectors.toList());
	}

	@PostMapping()
	public ClienteDto create(@RequestBody ClienteDto entity) {
		return mapper.map(clienteService.create(mapper.map(entity, Cliente.class)), ClienteDto.class);
	}


}
