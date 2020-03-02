package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	ClienteService service;

	@GetMapping("/clientes")
	public List<Cliente> findAll() {
		return service.find();
	}

	@GetMapping("/clientes/{id}")
	public Optional<Cliente> findCliente(@PathVariable int id) {
		return service.find(id);

	}

//    @PostMapping("/clientes/login")
//    public ClienteDto update(@RequestBody ClienteDto clienteDto) {
//    	Cliente cli = new Cliente();
//    	return service.update(cli);
//
//    }

}
