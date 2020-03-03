package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.eoi.mundobancario.dto.ClienteDto;
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
	private final ModelMapper mapper;
	private final ClienteService service;

//	@GetMapping("/clientes")
//	public List<Cliente> findAll() {
//		return service.find();
//	}

	@GetMapping("/clientes")
	public List<ClienteDto> find() {
		return service.find()
					  .stream()
					  .map(c -> mapper.map(c, ClienteDto.class))
					  .collect(Collectors.toList());
	}

	@GetMapping("/clientes/{id}")
	public ClienteDto findById(@PathVariable int id) {
		return mapper.map(
				service.find(id)
					   .orElseThrow(RuntimeException::new),
				ClienteDto.class
		);
	}

//    @PostMapping("/clientes/login")
//    public ClienteDto update(@RequestBody ClienteDto clienteDto) {
//    	Cliente cli = new Cliente();
//    	return service.update(cli);
//
//    }

}
