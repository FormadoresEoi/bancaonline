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
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	private ClienteService service;

	@Autowired
	private ModelMapper model;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody ClienteDto dto) {
		if (!service.find(dto.getId()).get().equals(null))
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else {
			service.create(model.map(dto, Cliente.class));
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDto> find(@PathVariable int id) {
		Optional<Cliente> cliente = service.find(id);
		if (!cliente.isPresent())
			return new ResponseEntity<ClienteDto>(HttpStatus.NOT_FOUND);
		ClienteDto dto = model.map(cliente.get(), ClienteDto.class);
		return new ResponseEntity<ClienteDto>(dto, HttpStatus.OK);
	}

	@GetMapping
	public List<ClienteDto> findAll() {
		List<ClienteDto> clientes = service.findAll().stream().map(c -> model.map(c, ClienteDto.class))
				.collect(Collectors.toList());

		return clientes;
	}

	@PutMapping(value = "/{id}")
	public void update(@RequestBody ClienteDto dto) {
		Cliente cliente = model.map(dto, Cliente.class);
		service.updateCliente(cliente);
	}
	
}
