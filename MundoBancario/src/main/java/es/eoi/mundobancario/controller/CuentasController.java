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

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {

	@Autowired
	CuentaService cuentaService;
	
	@Autowired
	MovimientoService movimientoService;
	
	@GetMapping(value = "/{id}")
	public CuentaDto FindById(@RequestParam(value = "id")int id) {
		Cuenta cuenta = cuentaService.FindById(id);
		CuentaDto dto = new CuentaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}
	
	@PostMapping
	public CuentaDto createCuenta(@RequestBody Cuenta cuenta) {;
		cuentaService.createCuenta(cuenta);
		CuentaDto dto = new CuentaDto(); 
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		dto.setCliente(cuenta.getCliente().getId());
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
		dto.setCliente(cuenta.getCliente().getId());
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
			cuentadto.setCliente(cuenta.getCliente().getId());
			dto.add(cuentadto);
		}
		return dto;
	}
	
	@GetMapping(value = "/deudores")
	public CuentaDto FindBySaldo() {
		Cuenta cuenta = cuentaService.FindBySaldo();
		CuentaDto dto = new CuentaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}
	
	@GetMapping(value = "/{id}/movimientos")
	public List<MovimientoDto> FindByCuenta(int cuenta) {
		List<Movimiento> litsMovimiento = movimientoService.findByCuenta(cuenta);
		List<MovimientoDto> dto = new ArrayList<MovimientoDto>();
		for (Movimiento movimiento : litsMovimiento) {
			MovimientoDto movimientodto = new MovimientoDto();
			movimientodto.setId(movimiento.getId());
			movimientodto.setDescripcion(movimiento.getDescripcion());
			movimientodto.setFecha(movimiento.getFecha());
			movimientodto.setImporte(movimiento.getImporte());
			movimientodto.setTipo(movimiento.getTipo().getId());
			movimientodto.setCuenta(movimiento.getCuenta().getNum_cuenta());
			dto.add(movimientodto);
		}
		return dto;
	}
	
	
}
