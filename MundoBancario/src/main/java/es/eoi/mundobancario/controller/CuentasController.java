package es.eoi.mundobancario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {

	@Autowired
	CuentaService cuentaService;
	
	@GetMapping(value = "/{id}")
	public CuentaDto FindById(@RequestParam(value = "id")int id) {
		Cuenta cuenta = cuentaService.FindById(id);
		CuentaDto dto = new CuentaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		//dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}
	
	@PostMapping
	public CuentaDto createCuenta(@RequestBody Cuenta cuenta) {;
		cuentaService.createCuenta(cuenta);
		CuentaDto dto = new CuentaDto(); 
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		//dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}
	
	@DeleteMapping
	public void deleteCuenta(@RequestBody Cuenta cuenta) {
		cuentaService.deleteCuenta(cuenta);
	}
	
	@PutMapping
	public CuentaDto updateCuenta(@RequestBody Cuenta cuenta) {
		cuentaService.updateCuenta(cuenta);
		CuentaDto dto = new CuentaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		//dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}
	
	@GetMapping
	public List<CuentaDto> listCuentas() {
		List<Cuenta> listCuentas = cuentaService.listCuentas();
		List<CuentaDto> dto = new ArrayList<CuentaDto>();
		for (Cuenta cuenta : listCuentas) {
			CuentaDto cuentadto = new CuentaDto();
			cuentadto.setNum_cuenta(cuenta.getNum_cuenta());
			cuentadto.setAlias(cuenta.getAlias());
			cuentadto.setSaldo(cuenta.getSaldo());
			//cuentadto.setCliente(cuenta.getCliente().getId());
			dto.add(cuentadto);
		}
		return dto;
	}
	
	public CuentaDto clienteToDto(Cuenta cuenta) {
		CuentaDto dto = new CuentaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		//dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}

	
}
