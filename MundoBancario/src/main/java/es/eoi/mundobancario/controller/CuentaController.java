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

import es.eoi.mundobancario.dto.CuentaDTOCreate;
import es.eoi.mundobancario.dto.CuentaDTOPrint;
import es.eoi.mundobancario.dto.MovimientoDTO;
import es.eoi.mundobancario.dto.PrestamoDTO;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.PrestamoService;


@RestController
@RequestMapping(value = "/cuentas")
public class CuentaController {

	@Autowired
	CuentaService cuentaserv;
	@Autowired
	ClienteService clientserv;

	@Autowired 
	PrestamoService prestamoserv;

	@Autowired
	MovimientoService movserv;


	@Autowired
	private ModelMapper modelmapper;

	@PostMapping
	public CuentaDTOCreate crearCuenta(@RequestBody CuentaDTOCreate dto) {
		Cuenta cuenta = modelmapper.map(dto, Cuenta.class);
		cuenta.setCliente(modelmapper.map(clientserv.buscarCliente(dto.getId_cliente()).get(),Cliente.class));
		CuentaDTOCreate resp = modelmapper.map(cuentaserv.InsertarCuenta(cuenta), CuentaDTOCreate.class);
		resp.setId_cliente(cuenta.getCliente().getId());
		return resp;
		}

	@DeleteMapping(value = "/{id}")
	public void removeCuenta(@PathVariable(value = "id") int id) {

		cuentaserv.removeCuenta(id);

	}

	@GetMapping
	public List<CuentaDTOPrint> mostrarCuentas() {
		Type listType = new TypeToken<List<CuentaDTOCreate>>() {
		}.getType();
		List<Cuenta> cuentas=cuentaserv.MostrarCuenta();
		List<CuentaDTOPrint> listcuentdto = modelmapper.map(cuentas, listType);
		for (int i = 0; i < listcuentdto.size(); i++) 
			listcuentdto.get(i).setId_cliente(cuentas.get(i).getCliente().getId());

		return listcuentdto;
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CuentaDTOCreate buscarCuenta(@PathVariable(value = "id") int id) {
		CuentaDTOCreate cuentadto = modelmapper.map(cuentaserv.buscarCuenta(id).get(),CuentaDTOCreate.class);
		
		return cuentadto;
	}
	

	@PostMapping (value = "/{id}/prestamos")
	public PrestamoDTO CrearPrestamo(@PathVariable int id, @RequestBody PrestamoDTO dto) {
	Cuenta cuenta=	cuentaserv.buscarCuenta(id).get();
	Prestamo prestamo= modelmapper.map(dto, Prestamo.class);
	prestamo.setCuenta(cuenta);
	Prestamo prestamofinal= prestamoserv.CrearPrestamo(prestamo);
	return modelmapper.map(prestamofinal, PrestamoDTO.class);
	
	}

	@PutMapping(value = "/update/{id}",params= {"alias"})
	public CuentaDTOPrint updateCuenta(@PathVariable (value="id") int id, @RequestParam (value="alias")String alias) {
		Cuenta cuenta = cuentaserv.buscarCuenta(id).get();
		cuenta.setAlias(alias);
		CuentaDTOPrint dtoprint = modelmapper.map(cuentaserv.updateCuenta(cuenta), CuentaDTOPrint.class);
		dtoprint.setId_cliente(cuenta.getCliente().getId());
		return dtoprint;

	}
	
	@GetMapping(value="/cuentas/deudoras")
	public List<CuentaDTOPrint> buscarCuentasDeudoras(){
		Type listType = new TypeToken<List<CuentaDTOPrint>>() {
		}.getType();
		List<Cuenta> cuentasdeudoras=cuentaserv.buscarCuentasDeudoras(0);
		List<CuentaDTOPrint> listcuentdto = modelmapper.map(cuentasdeudoras, listType);
		for (int i = 0; i < listcuentdto.size(); i++) 
			listcuentdto.get(i).setId_cliente(cuentasdeudoras.get(i).getCliente().getId());
		return listcuentdto;
	}
	
	@GetMapping(value="/cuentas/{id}/movimientos")
	public List<MovimientoDTO> buscarMovimientosCuenta(@PathVariable int id){
		Type listType = new TypeToken<List<MovimientoDTO>>() {
		}.getType();
		Cuenta cuenta =cuentaserv.buscarCuenta(id).get();
		return modelmapper.map(movserv.buscarMovimientosbyCuenta(cuenta),listType);

	}
}
