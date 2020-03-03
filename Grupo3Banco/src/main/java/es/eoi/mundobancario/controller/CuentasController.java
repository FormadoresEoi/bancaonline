package es.eoi.mundobancario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.CuentaDTO;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.service.CuentaService;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {
	
	@Autowired
	CuentaService service;

	@GetMapping(value = "{id}")
	public CuentaDTO FindById(@RequestParam(value = "id") int id) {
		Cuenta cuenta = service.FindById(id);
		CuentaDTO dto = new CuentaDTO();
		dto.setNumcuenta(cuenta.getNumcuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		return dto;
	}

//	@DeleteMapping
//	public void deleteCuenta(@RequestBody Cuenta cuenta) {
//		service.deleteCuenta(cuenta);
//	}

	@PutMapping("{id}")
	public void put(@PathVariable int id, String alias) {
		service.updateCuenta(service.FindById(id), alias);
	}

	@GetMapping
	public List<CuentaDTO> listCuentas() {
		List<Cuenta> listCuentas = service.listCuentas();
		List<CuentaDTO> dto = new ArrayList<CuentaDTO>();
		for (Cuenta cuenta : listCuentas) {
			CuentaDTO cuentadto = new CuentaDTO();
			cuentadto.setNumcuenta(cuenta.getNumcuenta());
			cuentadto.setAlias(cuenta.getAlias());
			cuentadto.setSaldo(cuenta.getSaldo());
			dto.add(cuentadto);
		}
		return dto;
	}
}