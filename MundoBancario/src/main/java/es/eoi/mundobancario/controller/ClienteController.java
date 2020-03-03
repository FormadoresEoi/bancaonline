package es.eoi.mundobancario.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDTO;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clientserv;
	@Autowired
	private ModelMapper modelmapper;

	@PostMapping
	public ClienteDTO crearCliente(@RequestBody ClienteDTO dto) {
		Cliente cliente = modelmapper.map(dto, Cliente.class);
		return modelmapper.map(clientserv.InsertarCliente(cliente), ClienteDTO.class);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeCliente(@PathVariable(value = "id") int id) {

		clientserv.removeCliente(id);

	}

	@GetMapping
	public List<ClienteDTO> mostrarCliente() {
		Type listType = new TypeToken<List<ClienteDTO>>() {
		}.getType();
		List<ClienteDTO> listbancdto = modelmapper.map(clientserv.MostrarCliente(), listType);
		return listbancdto;
	}

	@PutMapping(value = "/update")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ClienteDTO updateCliente(@RequestBody ClienteDTO clientdto) {
		Cliente cliente = modelmapper.map(clientdto, Cliente.class);
		return modelmapper.map(clientserv.updateCliente(cliente), ClienteDTO.class);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ClienteDTO selectCliente(@PathVariable(value = "id") int id) {
		ClienteDTO clientdto = modelmapper.map(clientserv.buscarCliente(id).get(), ClienteDTO.class);
		// BeanUtils.copyProperties(bancdto,bncserv.buscarBanco(nombre)); OTRA FORMA DE
		// PASAR EL DTO
		return clientdto;

	}
}
