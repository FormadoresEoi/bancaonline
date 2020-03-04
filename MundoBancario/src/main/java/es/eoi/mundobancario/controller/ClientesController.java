package es.eoi.mundobancario.controller;

import static es.eoi.mundobancario.utils.DtoConverter.fromDto;
import static es.eoi.mundobancario.utils.DtoConverter.toDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteNuevoDto;
import es.eoi.mundobancario.dto.CuentaBasicaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public List<ClienteDto> getAll() {
		List<ClienteDto> dtolist = new ArrayList<ClienteDto>();
		for (Cliente cliente : clienteService.getAll())
			dtolist.add(toDto(cliente));
		return dtolist;
	}

	@GetMapping("/{id}")
	public ClienteDto getById(@PathVariable("id") Integer id) {
		return toDto((clienteService.getById(id)));
	}

	@PostMapping("/login")
	public ClienteDto postLogin(@RequestParam String usuario, @RequestParam String pass) {
		return toDto(clienteService.getByUsuarioAndPass(usuario, pass));
	}

	@GetMapping("/{id}/cuentas")
	public List<CuentaBasicaDto> getAllCuentas(@PathVariable Integer id) {
		List<CuentaBasicaDto> dtolist = new ArrayList<CuentaBasicaDto>();
		for (Cuenta cuenta : clienteService.getById(id).getCuentas())
			dtolist.add(toDto(cuenta));
		return dtolist;
	}

	@PutMapping("/{id}")
	public boolean putEmail(@PathVariable Integer id, @RequestParam String email) {
		return clienteService.putEmail(id, email);
	}

	@PostMapping
	public boolean postCliente(@RequestBody ClienteNuevoDto dto) {
		return clienteService.post(fromDto(dto));
	}

}
