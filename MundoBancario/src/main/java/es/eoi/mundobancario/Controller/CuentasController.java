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
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;

@RestController
@RequestMapping(value="/cuentas")
public class CuentasController {
	
	@Autowired
	CuentaService service;
	@Autowired
	ModelMapper modelMapper;
	
	private CuentaDto toDto(Cuenta cuenta) {
		CuentaDto cuentaDto = modelMapper.map(cuenta, CuentaDto.class);
		return cuentaDto;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamos")
	public CuentaDto findPrestamos(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
	//TODO elegir metodo implementacion
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamosVivos")
	public CuentaDto findPrestamosVivos(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamosAmortizados")
	public CuentaDto findPrestamosAmortizados(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/prestamos")
	public CuentaDto createPrestamos(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/prestamos")
	public CuentaDto createIngresos(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/prestamos")
	public CuentaDto createPagos(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
}
