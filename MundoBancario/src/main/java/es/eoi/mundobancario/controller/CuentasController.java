package es.eoi.mundobancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.service.CuentaService;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {

	@Autowired
	CuentaService service;
	
	@GetMapping
	public Cuenta FindById(@RequestParam(value = "id")int id) {
		return service.FindById(id);
	}
	
	@PostMapping
	public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
		return service.createCuenta(cuenta);
	}
	
	@DeleteMapping
	public void deleteCuenta(@RequestBody Cuenta cuenta) {
		service.deleteCuenta(cuenta);
	}
	
	
	
}
