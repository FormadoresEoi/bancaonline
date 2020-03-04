package es.eoi.mundobancario.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDTO;
import es.eoi.mundobancario.dto.ClienteLoginDTO;
import es.eoi.mundobancario.dto.CuentaDTO;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClientesController {

	private final ClienteService service;
	private final ModelMapper mapper;
	
	// Devuelve todos los clientes
	@GetMapping
	public List<ClienteDTO> getTodos(){
		return mapper.map(service.findAll(),new TypeToken<List<ClienteDTO>>() {
		}.getType());
	}
	// Devuelve el cliente solicitado
	@GetMapping("{id}")
	public ClienteDTO getCliente(@PathVariable int id) {
		return mapper.map(service.findById(id),ClienteDTO.class);
	}
	// Devuelve las cuentas del cliente solicitado
	@GetMapping("/cuentas/{id}")
	public List<CuentaDTO> getCuentas(@PathVariable int id) {
        return mapper.map(service.findById(id).getCuentas(), new TypeToken<List<CuentaDTO>>() {
        }.getType());
    }
	// Creamos cliente
	@PostMapping
	public void post(@RequestBody ClienteDTO cliente) {
		service.createClient(mapper.map(cliente,es.eoi.mundobancario.entity.Cliente.class));
	}
	// Modificar el email del cliente
	@PutMapping("{id}")
	public void put(@PathVariable int id, String email) {
        service.updateClient(service.findById(id), email);
    } 
	//Devuelve el cliente solicitado con el login
	@PostMapping("/login")
	public ClienteLoginDTO post(@RequestBody String usuario,String pass) {
		List<Cliente> clientes = service.findAll();
		Cliente c = null;
		int id = 0;
		for(int i = 0; i < clientes.size(); i++)
		{
			if (clientes.get(i).getUsuario().equals(usuario) && clientes.get(i).getPass().equals(pass))
			{
				 c = clientes.get(i);
				 id = c.getId();
			}
			
		}
		return mapper.map(service.findById(id),ClienteLoginDTO.class);
	}
}