package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteBasicoDto;
import es.eoi.mundobancario.dto.NewClienteDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ModelMapper model;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody NewClienteDto dto) {
		if (clienteService.find(dto.getId()).isPresent())
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			clienteService.create(model.map(dto, Cliente.class));
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	}

	@PostMapping(value = "/login")
	public ResponseEntity<ClienteBasicoDto> findByUsuarioAndPass(@RequestParam String usuario, @RequestParam String pass) {
		Optional<Cliente> cliente = clienteService.findByUsuarioAndPass(usuario, pass);
		if (!cliente.isPresent())
			return new ResponseEntity<ClienteBasicoDto>(HttpStatus.NOT_FOUND);
		ClienteBasicoDto dto = model.map(cliente.get(), ClienteBasicoDto.class);
		return new ResponseEntity<ClienteBasicoDto>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteBasicoDto> findById(@PathVariable int id) {
		Optional<Cliente> cliente = clienteService.find(id);
		if (!cliente.isPresent())
			return new ResponseEntity<ClienteBasicoDto>(HttpStatus.NOT_FOUND);
		ClienteBasicoDto dto = model.map(cliente.get(), ClienteBasicoDto.class);
		return new ResponseEntity<ClienteBasicoDto>(dto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ClienteBasicoDto>> findAll() {
		List<ClienteBasicoDto> clientes = clienteService.findAll()
				.stream()
				.map(c -> model.map(c, ClienteBasicoDto.class))
				.collect(Collectors.toList());

		return new ResponseEntity<List<ClienteBasicoDto>>(clientes, HttpStatus.OK);
	}
	
//	@GetMapping
//	public ResponseEntity<List<CuentaDto>> findAllCuentasByCliente(@PathVariable int id) {
//		List<CuentaDto> cuentas = clienteService.findAllCuentasByIdCliente(id)
//				.stream()
//				.map(c -> model.map(c, CuentaDto.class))
//				.collect(Collectors.toList());
//
//		return new ResponseEntity<List<CuentaDto>>(cuentas, HttpStatus.OK);
//	}
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteBasicoDto> update(@PathVariable int id, @RequestParam String email) {
		Cliente cliente = clienteService.find(id).get();
		cliente.setEmail(email);
		clienteService.updateCliente(cliente);
		ClienteBasicoDto modifyCliente = model.map(cliente, ClienteBasicoDto.class);
		return new ResponseEntity<ClienteBasicoDto>(modifyCliente, HttpStatus.OK);
	}
	
}
