package es.eoi.mundobancario.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.Service.ClienteService;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteDtoCuentas;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.util.DtoConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClientesController {
	@Autowired
	ClienteService service;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	DtoConstructor dtoConstructor;

	@RequestMapping(method = RequestMethod.GET, value = "/clientes")
	public List<ClienteDto> findAll() {
		List<ClienteDto> clientes = new ArrayList<ClienteDto>();
		for (Cliente cliente : service.findAll()) {
			clientes.add(dtoConstructor.toClienteDto(cliente));
		}
		return clientes;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ClienteDto findCliente(@PathVariable int id) {
		return dtoConstructor.toClienteDto(service.findById(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ClienteDto login(@RequestParam String user, @RequestParam String pass) {
		return dtoConstructor.toClienteDto(service.login(user, pass));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/cuentas")
	public ClienteDtoCuentas findCuentas(@PathVariable int id) {
		return dtoConstructor.toClienteDtoCuentas(service.findCuentas(id));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ClienteDto updateEmail(@PathVariable int id, @RequestParam String email) {
		Cliente cliente = service.findById(id);
		cliente.setEmail(email);
		return dtoConstructor.toClienteDto(service.update(cliente));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/clientes")
	public ClienteDto create(@RequestParam String usuario, @RequestParam String nombre, @RequestParam String pass,
			@RequestParam String email) {
		return dtoConstructor.toClienteDto(service.Create(usuario, nombre, pass, email));

	}
}