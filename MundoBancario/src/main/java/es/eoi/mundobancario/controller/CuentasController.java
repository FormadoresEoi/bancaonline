package es.eoi.mundobancario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.CuentaBasicaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.TipoMovimientoService;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.PrestamoService;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {

	@Autowired
	CuentaService cuentaService;
	
	@Autowired
	MovimientoService movimientoService;
	
	@Autowired
	TipoMovimientoService tipoMovimientoService;
	PrestamoService prestamoService;

	
	@GetMapping(value = "/{id}")
	public CuentaBasicaDto FindById(@RequestParam(value = "id")int id) {
		Cuenta cuenta = cuentaService.FindById(id);
		CuentaBasicaDto dto = new CuentaBasicaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		//dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}
	
	@PostMapping
	public CuentaBasicaDto createCuenta(@RequestBody Cuenta cuenta) {;
		cuentaService.createCuenta(cuenta);
		CuentaBasicaDto dto = new CuentaBasicaDto(); 
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
	public CuentaBasicaDto updateCuenta(@RequestBody Cuenta cuenta) {
		cuentaService.updateCuenta(cuenta);
		CuentaBasicaDto dto = new CuentaBasicaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		//dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}
	
	@GetMapping
	public List<CuentaBasicaDto> listCuentas() {
		List<Cuenta> listCuentas = cuentaService.listCuentas();
		List<CuentaBasicaDto> dto = new ArrayList<CuentaBasicaDto>();
		for (Cuenta cuenta : listCuentas) {
			CuentaBasicaDto cuentadto = new CuentaBasicaDto();
			cuentadto.setNum_cuenta(cuenta.getNum_cuenta());
			cuentadto.setAlias(cuenta.getAlias());
			cuentadto.setSaldo(cuenta.getSaldo());
			//cuentadto.setCliente(cuenta.getCliente().getId());
			dto.add(cuentadto);
		}
		return dto;
	}

	@GetMapping(value = "/deudores")
	public CuentaBasicaDto FindBySaldo() {
		Cuenta cuenta = cuentaService.FindBySaldo();
		CuentaBasicaDto dto = new CuentaBasicaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
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
			dto.add(movimientodto);
		}
		return dto; 
	}
	@GetMapping(value = "/{id}/prestamos")
	public List<PrestamoDto> findByCuenta(int cuenta) {
		List<Prestamo> listPrestamos = prestamoService.FindByCuenta(cuenta);
		List<PrestamoDto> dto = new ArrayList<PrestamoDto>();
		for (Prestamo prestamo : listPrestamos) {
			PrestamoDto prestamodto = new PrestamoDto();
			prestamodto.setId(prestamo.getId());
			prestamodto.setDescrpicon(prestamo.getDescrpicon());
			prestamodto.setFecha(prestamo.getFecha());
			prestamodto.setImporte(prestamo.getImporte());
			prestamodto.setPlazos(prestamo.getPlazos());
			dto.add(prestamodto);
		}
		return dto;
	}
	
	@PostMapping("/{id}/ingresos")
	public String postIngreso(@PathVariable Integer id, @RequestBody MovimientoDto dto) {
		Cuenta cuenta = cuentaService.FindById(id);
		Movimiento movimiento = new Movimiento();
		TipoMovimiento lol = tipoMovimientoService.FindById(1);
		movimiento.setImporte(dto.getImporte());
		movimiento.setTipo(lol);
		movimiento.setDescripcion(dto.getDescripcion());
		movimiento.setCuenta(cuenta);
		System.out.println(cuenta.getMovimientos().toString());
		movimientoService.createMovimiento(movimiento);
		return "OK";
	}
	
	@PostMapping("/{id}/pagos")
	public String postPagos(@PathVariable Integer id, @RequestBody MovimientoDto dto) {
		Cuenta cuenta = cuentaService.FindById(id);
		Movimiento movimiento = new Movimiento();
		TipoMovimiento lol = tipoMovimientoService.FindById(2);
		movimiento.setImporte(dto.getImporte());
		movimiento.setTipo(lol);
		movimiento.setDescripcion(dto.getDescripcion());
		movimiento.setCuenta(cuenta);
		System.out.println(cuenta.getMovimientos().toString());
		movimientoService.createMovimiento(movimiento);
		return "OK";
	}
	
	@PostMapping("/{id}/prestamos")
	public String createPrestamo(@RequestParam(value = "importe") float importe, @RequestParam(value = "plazos") int plazos) {
		Movimiento movimiento = movimientoService.createIngreso(importe, plazos);
		TipoMovimiento lol = tipoMovimientoService.FindById(2);
		Cuenta cuenta = cuentaService.FindById(4);
		MovimientoDto dto = new MovimientoDto();
		movimiento.setImporte(dto.getImporte());
		movimiento.setTipo(lol);
		movimiento.setDescripcion(dto.getDescripcion());
		movimiento.setFecha(dto.getFecha());
		movimiento.setCuenta(cuenta);
		movimientoService.createMovimiento(movimiento);
		return "ok";
	}
	

	@GetMapping(value = "/{id}/prestamosVivo")
	public PrestamoDto FindByPrestamoVivo(int cuenta) {
		Prestamo prestamo = prestamoService.FindByPrestamoVivo(cuenta);
		PrestamoDto dto = new PrestamoDto();
		dto.setId(prestamo.getId());
		dto.setDescrpicon(prestamo.getDescrpicon());
		dto.setFecha(prestamo.getFecha());
		dto.setImporte(prestamo.getImporte());
		dto.setPlazos(prestamo.getPlazos());
		return dto;
	}

	public CuentaBasicaDto clienteToDto(Cuenta cuenta) {
		CuentaBasicaDto dto = new CuentaBasicaDto();
		dto.setNum_cuenta(cuenta.getNum_cuenta());
		dto.setAlias(cuenta.getAlias());
		dto.setSaldo(cuenta.getSaldo());
		//dto.setCliente(cuenta.getCliente().getId());
		return dto;
	}


	
}
