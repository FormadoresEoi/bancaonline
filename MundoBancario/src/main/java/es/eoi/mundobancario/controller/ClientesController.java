package es.eoi.mundobancario.controller;

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
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	CuentaService cuentaService;

	@GetMapping
	public List<ClienteDto> getAll() {
		List<ClienteDto> dtoList = new ArrayList<ClienteDto>();
		for (Cliente cliente : clienteService.getAll())
			dtoList.add(clienteToDto(cliente));
		return dtoList;
	}

	@GetMapping("/{id}")
	public ClienteDto getById(@PathVariable("id") Integer id) {
		return clienteToDto(clienteService.getById(id));
	}

	@PostMapping("/login")
	public ClienteDto postLogin(@RequestParam String usuario, @RequestParam String pass) {
		return clienteToDto(clienteService.getByUsuarioAndPass(usuario, pass));
	}

	// TODO Traer cuentas de cliente

	@PutMapping("/{id}")
	public String putEmail(@PathVariable("id") Integer id, @RequestParam String email) {
		if(clienteService.putEmail(id, email)) return "Correo actualizado correctamente";
		else return "No se ha podido actualizar el correo";
	}

	@PostMapping
	public String postCliente(@RequestBody ClienteDto dto, @RequestParam String pass) {
		Cliente cliente = dtoToCliente(dto);
		cliente.setPass(pass);
		if(clienteService.post(cliente))
			return "Cliente creado correctamente";
		else
			return "El cliente no se ha podido crear";
	}

	private ClienteDto clienteToDto(Cliente cliente) {
		ClienteDto dto = new ClienteDto();
		dto.setId(cliente.getId());
		dto.setUsuario(cliente.getUsuario());
		dto.setNombre(cliente.getNombre());
		dto.setEmail(cliente.getEmail());
		return dto;
	}

	private Cliente dtoToCliente(ClienteDto dto) {
		Cliente cliente = new Cliente();
		cliente.setId(dto.getId());
		cliente.setUsuario(dto.getUsuario());
		cliente.setNombre(dto.getNombre());
		cliente.setEmail(dto.getEmail());
		return cliente;
	}

}
