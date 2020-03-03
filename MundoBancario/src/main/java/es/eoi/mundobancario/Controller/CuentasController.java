package es.eoi.mundobancario.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.Service.CuentaService;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cuenta;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentasController {

	@Autowired
	CuentaService service;
	@Autowired
	ModelMapper modelMapper;

	private CuentaDto toDto(Cuenta cuenta) {
		CuentaDto cuentaDto = modelMapper.map(cuenta, CuentaDto.class);
		return cuentaDto;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CuentaDto> findAll() {
		List<CuentaDto> cuentas = new ArrayList<CuentaDto>();
		for (Cuenta cuenta : service.findAll()) {
			cuentas.add(toDto(cuenta));
		}
		return cuentas;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/deudoras")
	public List<CuentaDto> findAllDeudoras() {
		List<CuentaDto> cuentas = new ArrayList<CuentaDto>();
		for (Cuenta cuenta : service.findAllDeudora()) {
			cuentas.add(toDto(cuenta));
		}
		return cuentas;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CuentaDto findById(@PathVariable int id) {
		return toDto(service.findById(id));
	}

	@RequestMapping(method = RequestMethod.POST)
	public CuentaDto Create(@RequestParam("alias") String alias, @RequestParam("saldo") String saldo,
			@RequestParam("idCliente") int idCliente) {
		Cuenta cuenta = new Cuenta(alias, saldo, idCliente);
		return toDto(service.create(cuenta));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public CuentaDto updateAlias(@PathVariable int id, @RequestParam("alias") String alias) {
		Cuenta cuenta = service.findById(id);
		cuenta.setAlias(alias);
		return toDto(service.update(cuenta));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/movimientos")
	public CuentaDto findMovimientos(@PathVariable int id) {
		return toDto(service.findMovimientos(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamos")
	public CuentaDto findPrestamos(@PathVariable int id) {
		return toDto(service.findPresatmos(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamosVivos")
	public CuentaDto findPrestamosVivos(@PathVariable int id) {
		return toDto(service.findPrestamosVivos(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamosAmortizados")
	public CuentaDto findPrestamosAmortizados(@PathVariable int id) {
		return toDto(service.findPrestamosAmortizados(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/prestamos")
	public CuentaDto createPrestamos(@PathVariable int id) {
		return toDto(service.createPrestamos(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/ingresos")
	public CuentaDto createIngresos(@PathVariable int id) {
		return toDto(service.createIngresos(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/pagos")
	public CuentaDto createPagos(@PathVariable int id) {
		return toDto(service.createPagos(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/ejecutarAmortizacionsDiarias")
	public void ejecutarAmortizacionsDiarias(@PathVariable int id) {
		service.ejecutarAmortizacionsDiarias();
	}
}
