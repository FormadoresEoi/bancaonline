package es.eoi.mundobancario.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.Service.ClienteService;
import es.eoi.mundobancario.Service.CuentaService;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.CuentaDtoMovimientos;
import es.eoi.mundobancario.dto.CuentaDtoPrestamos;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.util.DtoConstructor;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {

	@Autowired
	CuentaService service;
	@Autowired
	ClienteService clienteService;
	@Autowired
	DtoConstructor dtoConstructor;

	

	@RequestMapping(method = RequestMethod.GET)
	public List<CuentaDto> findAll() {
		List<CuentaDto> cuentas = new ArrayList<CuentaDto>();
		for (Cuenta cuenta : service.findAll()) {
			cuentas.add(dtoConstructor.toCuentaDto(cuenta));
		}
		return cuentas;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/deudoras")
	public List<CuentaDto> findAllDeudoras() {
		List<CuentaDto> cuentas = new ArrayList<CuentaDto>();
		for (Cuenta cuenta : service.findAll()) {
				cuentas.add(dtoConstructor.toCuentaDto(cuenta));
		}
		return cuentas;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CuentaDto findById(@PathVariable int id) {
		return dtoConstructor.toCuentaDto(service.findById(id));
	}

	@RequestMapping(method = RequestMethod.POST)
	public CuentaDto Create(@RequestParam("alias") String alias, @RequestParam("saldo") double saldo,
			@RequestParam("idCliente") int idCliente) {
		Cuenta cuenta = new Cuenta(alias, saldo);
		cuenta.setCliente(clienteService.findById(idCliente));
		return dtoConstructor.toCuentaDto(service.create(cuenta));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public CuentaDto updateAlias(@PathVariable int id, @RequestParam("alias") String alias) {
		Cuenta cuenta = service.findById(id);
		cuenta.setAlias(alias);
		return dtoConstructor.toCuentaDto(service.update(cuenta));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/movimientos")
	public CuentaDtoMovimientos findMovimientos(@PathVariable int id) {
		return dtoConstructor.toCuentaDtoMovimientos(service.findById(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamos")
	public CuentaDtoPrestamos findPrestamos(@PathVariable int id) {
		return dtoConstructor.toCuentaDtoPrestamos(service.findById(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamosVivos")
	public CuentaDtoPrestamos findPrestamosVivos(@PathVariable int id) {
		return dtoConstructor.toCuentaDtoPrestamos(service.findPrestamosVivos(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamosAmortizados")
	public CuentaDto findPrestamosAmortizados(@PathVariable int id) {
		return dtoConstructor.toCuentaDto(service.findPrestamosAmortizados(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/prestamos")
	public PrestamoDto createPrestamos(@PathVariable int id, @RequestParam("descripcion") String descripcion,
			@RequestParam("importe") double importe, @RequestParam("plazo") int plazo) {
		Prestamo prestamo = new Prestamo(descripcion, new Date(), importe, plazo);
		Movimiento movimiento = new Movimiento(descripcion, new Date(), importe);
		return dtoConstructor.toPrestamoDto(service.createPrestamos(prestamo, movimiento, id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/ingresos")
	public MovimientoDto createIngresos(@PathVariable int id, @RequestParam("descripcion") String descripcion,
			@RequestParam("importe") double importe) {
		Movimiento movimiento = new Movimiento(descripcion, new Date(), importe);
		return dtoConstructor.toMovimientoDto(service.createIngresos(movimiento));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/pagos")
	public MovimientoDto createPagos(@PathVariable int id, @RequestParam("descripcion") String descripcion,
			@RequestParam("importe") double importe) {
		Movimiento movimiento = new Movimiento(descripcion, new Date(), importe);
		return dtoConstructor.toMovimientoDto(service.createPagos(movimiento));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ejecutarAmortizacionsDiarias")
	public void ejecutarAmortizacionsDiarias(@PathVariable int id) {
		service.ejecutarAmortizacionsDiarias();
	}
}
