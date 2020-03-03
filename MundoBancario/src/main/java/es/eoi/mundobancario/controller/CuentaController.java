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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.CuentaDTO;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;

@RestController
@RequestMapping(value = "/cuenta")
public class CuentaController {

	@Autowired
	CuentaService cuentaserv;
	@Autowired
	ClienteService clientserv;

	@Autowired
	private ModelMapper modelmapper;

	@PostMapping
	public CuentaDTO crearCuenta(@RequestBody CuentaDTO dto) {
		Cuenta cuenta = modelmapper.map(dto, Cuenta.class);
		cuenta.setCliente(modelmapper.map(clientserv.buscarCliente(dto.getId_cliente()).get(),Cliente.class));
		CuentaDTO resp = modelmapper.map(cuentaserv.InsertarCuenta(cuenta), CuentaDTO.class);
		resp.setId_cliente(cuenta.getCliente().getId());
		return resp;
		}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeCuenta(@PathVariable(value = "id") int id) {

		cuentaserv.removeCuenta(id);

	}

	@GetMapping
	public List<CuentaDTO> mostrarCuenta() {
		Type listType = new TypeToken<List<CuentaDTO>>() {
		}.getType();
		List<CuentaDTO> listcuentdto = modelmapper.map(cuentaserv.MostrarCuenta(), listType);
		return listcuentdto;
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CuentaDTO selectCuenta(@PathVariable(value = "id") int id) {
		CuentaDTO cuentadto = modelmapper.map(cuentaserv.buscarCuenta(id).get(),CuentaDTO.class);
		
		return cuentadto;
	}
}
