package es.eoi.mundobancario.controller;

import static es.eoi.mundobancario.utils.DtoConverter.fromClienteNuevoDto;
import static es.eoi.mundobancario.utils.DtoConverter.toClienteDto;
import static es.eoi.mundobancario.utils.DtoConverter.toClienteDtoList;
import static es.eoi.mundobancario.utils.DtoConverter.toCuentaBasicaDtoList;

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
import es.eoi.mundobancario.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public List<ClienteDto> getAll() {
		return toClienteDtoList(clienteService.getAll());
	}

	@GetMapping("/{id}")
	public ClienteDto getById(@PathVariable("id") Integer id) {
		return toClienteDto((clienteService.getById(id)));
	}

	@PostMapping("/login")
	public ClienteDto postLogin(@RequestParam String usuario, @RequestParam String pass) {
		return toClienteDto(clienteService.getByUsuarioAndPass(usuario, pass));
	}

	@GetMapping("/{id}/cuentas")
	public List<CuentaBasicaDto> getAllCuentas(@PathVariable Integer id) {
		return toCuentaBasicaDtoList(clienteService.getById(id).getCuentas());
	}

	@PutMapping("/{id}")
	public boolean putEmail(@PathVariable Integer id, @RequestParam String email) {
		return clienteService.putEmail(id, email);
	}

	@PostMapping
	public boolean postCliente(@RequestBody ClienteNuevoDto dto) {
		return clienteService.post(fromClienteNuevoDto(dto));
	}

}
