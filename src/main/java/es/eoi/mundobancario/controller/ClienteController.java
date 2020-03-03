package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.service.ClienteService;
import lombok.RequiredArgsConstructor;

/**
 * Cliente controller ===============
 * 
 * Controller for the ClienteDTO.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */

@RequiredArgsConstructor
@RequestMapping
@RestController
public class ClienteController {
	
	@Autowired
	private final ModelMapper mapper;
	
	@Autowired
	private final ClienteService service;

//	@GetMapping("/clientes")
//	public List<Cliente> findAll() {
//		return service.find();
//	}

	@GetMapping("/clientes")
	public List<ClienteDto> find() {
		return service.find().stream().map(c -> mapper.map(c, ClienteDto.class)).collect(Collectors.toList());
	}

	@GetMapping("/clientes/{id}")
	public ClienteDto findById(@PathVariable int id) {
		return mapper.map(service.find(id).orElseThrow(RuntimeException::new), ClienteDto.class);
	}

	@PostMapping("/clientes/login")
	public ClienteDto findClienteDto(@PathVariable int id, String usuario, String nombre, String email) {
		return mapper.map(service.find(id), ClienteDto.class);
	}
	
	@GetMapping("/clientes/{id}/cuentas")
	public CuentaDto find(@PathVariable int clientesId){
		return mapper.map(service.find(clientesId), CuentaDto.class);
	}
	
//	@RequestMapping(value = "/clientes/{id}", method = UPDATE)

}
