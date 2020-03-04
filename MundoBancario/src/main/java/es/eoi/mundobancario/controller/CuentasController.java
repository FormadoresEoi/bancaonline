package es.eoi.mundobancario.controller;

import static es.eoi.mundobancario.utils.DtoConverter.fromCuentaNuevaDto;
import static es.eoi.mundobancario.utils.DtoConverter.fromMovimientoNuevoDto;
import static es.eoi.mundobancario.utils.DtoConverter.toCuentaConClienteDto;
import static es.eoi.mundobancario.utils.DtoConverter.toCuentaConClienteDtoList;
import static es.eoi.mundobancario.utils.DtoConverter.toMovimientoDtoList;
import static es.eoi.mundobancario.utils.DtoConverter.toPrestamoDtoList;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.CuentaConClienteDto;
import es.eoi.mundobancario.dto.CuentaNuevaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.MovimientoNuevoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.PrestamoService;
import es.eoi.mundobancario.service.TipoMovimientoService;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {

	@Autowired
	CuentaService cuentaService;

	@Autowired
	MovimientoService movimientoService;

	@Autowired
	TipoMovimientoService tipoMovimientoService;

	@Autowired
	PrestamoService prestamoService;
	
	@Autowired
	ClienteService clienteService;

	@GetMapping
	public List<CuentaConClienteDto> getAll() {
		return toCuentaConClienteDtoList(cuentaService.getAll());
	}

	@GetMapping("/deudoras")
	public List<CuentaConClienteDto> getBySaldo() {
		return toCuentaConClienteDtoList(cuentaService.getDeudoras());
	}

	@GetMapping("/{id}")
	public CuentaConClienteDto getById(@PathVariable Integer id) {
		return toCuentaConClienteDto(cuentaService.getById(id));
	}

	@PostMapping
	public boolean post(@RequestBody CuentaNuevaDto dto, @RequestParam Integer idcliente) {
		Cuenta cuenta = fromCuentaNuevaDto(dto);
		cuenta.setCliente(clienteService.getById(idcliente));
		return cuentaService.post(cuenta);
	}

	@PutMapping("/{id}")
	public boolean updateCuenta(@PathVariable Integer id, @RequestParam String alias) {
		return cuentaService.putAlias(id, alias);
	}

	@GetMapping(value = "/{id}/movimientos")
	public List<MovimientoDto> getMovimientosByCuenta(@PathVariable Integer id) {
		return toMovimientoDtoList(movimientoService.getByCuenta(cuentaService.getById(id)));
	}

	@GetMapping(value = "/{id}/prestamos")
	public List<PrestamoDto> findByCuenta(@PathVariable Integer id) {
		return toPrestamoDtoList(prestamoService.getByCuenta(cuentaService.getById(id)));
	}

	@PostMapping("/{id}/ingresos")
	public boolean postIngreso(@PathVariable Integer id, @RequestBody MovimientoNuevoDto dto) {
		Movimiento movimiento = fromMovimientoNuevoDto(dto);
		movimiento.setCuenta(cuentaService.getById(id));
		movimiento.setFecha(new Timestamp((new Date()).getTime()));
		movimiento.setTipo(tipoMovimientoService.getByTipo("INGRESO"));
		return movimientoService.post(movimiento);
	}

	@PostMapping("/{id}/pagos")
	public boolean postPagos(@PathVariable Integer id, @RequestBody MovimientoNuevoDto dto) {
		Movimiento movimiento = fromMovimientoNuevoDto(dto);
		movimiento.setCuenta(cuentaService.getById(id));
		movimiento.setFecha(new Timestamp((new Date()).getTime()));
		movimiento.setTipo(tipoMovimientoService.getByTipo("PAGO"));
		return movimientoService.post(movimiento);
	}
	
	@PostMapping("/{id}/prestamos")
	public String createPrestamo(@RequestParam(value = "importe") float importe, @RequestParam(value = "plazos") int plazos) {
//		Movimiento movimiento = movimientoService.post(importe, plazos);
//		TipoMovimiento lol = tipoMovimientoService.FindById(2);
//		Cuenta cuenta = cuentaService.FindById(4);
//		MovimientoDto dto = new MovimientoDto();
//		movimiento.setImporte(dto.getImporte());
//		movimiento.setTipo(lol);
//		movimiento.setDescripcion(dto.getDescripcion());
//		movimiento.setFecha(dto.getFecha());
//		movimiento.setCuenta(cuenta);
//		movimientoService.post(movimiento);
		return "ok";
	}
	

	@GetMapping(value = "/{id}/prestamosVivo")
	public PrestamoDto FindByPrestamoVivo(int cuenta) {
//		List<Prestamo> prestamo = prestamoService.getPrestamosVivos(cuenta);
//		PrestamoDto dto = new PrestamoDto();
//		dto.setId(prestamo.getId());
//		dto.setDescrpicon(prestamo.getDescrpicon());
//		dto.setFecha(prestamo.getFecha());
//		dto.setImporte(prestamo.getImporte());
//		dto.setPlazos(prestamo.getPlazos());
//		return dto;
		return null;
	}

}
