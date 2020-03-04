package es.eoi.mundobancario.controller;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDTOCreate;
import es.eoi.mundobancario.dto.ClienteDTOLogin;
import es.eoi.mundobancario.dto.ClienteDTOPrint;
import es.eoi.mundobancario.dto.CuentaDTOCreate;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clientserv;
	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private CuentaService cuentaService;

	@PostMapping
	public ClienteDTOPrint crearCliente(@RequestBody ClienteDTOCreate dto) {
		Cliente cliente = modelmapper.map(dto, Cliente.class);
		return modelmapper.map(clientserv.InsertarCliente(cliente), ClienteDTOPrint.class);
	}

	@DeleteMapping(value = "/{id}")
	public String removeCliente(@PathVariable(value = "id") int id) {

		return clientserv.removeCliente(id);

	}

	@GetMapping
	public List<ClienteDTOPrint> mostrarCliente() {
		Type listType = new TypeToken<List<ClienteDTOPrint>>() {
		}.getType();
		List<ClienteDTOPrint> listbancdto = modelmapper.map(clientserv.MostrarCliente(), listType);
		return listbancdto;
	}

	@PutMapping(value = "/update/{id}", params = { "email" })
	public ClienteDTOPrint updateCliente(@PathVariable(value = "id") int id,
			@RequestParam(value = "email") String email) {
		Cliente cliente = modelmapper.map(clientserv.buscarCliente(id).get(), Cliente.class);
		cliente.setEmail(email);
		return modelmapper.map(clientserv.updateCliente(cliente), ClienteDTOPrint.class);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ClienteDTOPrint selectCliente(@PathVariable(value = "id") int id) {
		ClienteDTOPrint clientdto = modelmapper.map(clientserv.buscarCliente(id).get(), ClienteDTOPrint.class);
		return clientdto;

	}

	@RequestMapping(value = "/{id}/cuentas", method = RequestMethod.GET)
	public List<CuentaDTOCreate> findAllById_Clientes(@PathVariable(value = "id") int id) {
		Cliente cliente = clientserv.buscarCliente(id).get();
		Type listType = new TypeToken<List<CuentaDTOCreate>>() {
		}.getType();
		List<CuentaDTOCreate> listclidto = modelmapper.map(cuentaService.findAllById_Clientes(cliente), listType);
		return listclidto;
	}

	@PostMapping(value = "/login")
	public ClienteDTOPrint findByUsuarioAndPass(@RequestBody ClienteDTOLogin login) {
		Cliente cliente = clientserv.findByUsuarioAndPass(login.getUsuario(), login.getPass());
		return modelmapper.map(cliente, ClienteDTOPrint.class);

	}

}
