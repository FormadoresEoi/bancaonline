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
		CuentaDto cuentaDto = mp.map(banco, BancoDto.class);
		return cuentaDto;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamos")
	public CuentaDto findPrestamos(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamos")
	public CuentaDto findPrestamosVivos(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/prestamos")
	public CuentaDto findPrestamosAmortizados(@PathVariable int id) {
		return toDto(service.findCuentaById(id).get());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public BancoDto FindId(@PathVariable int id) {
		return toDto(sv.FindId(id));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<BancoDto> FindAll() {
		List<BancoDto> bancos = new ArrayList<BancoDto>();
		for (Banco banco : sv.FindAll()) {
			bancos.add(toDto(banco));
		}
		return bancos;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public BancoDto Update(@PathVariable int id, @RequestParam("nombre") String nombre, @RequestParam("ciudad") String ciudad) {
		return toDto(sv.Update(id, nombre, ciudad));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void Delete(@PathVariable int id) {
		sv.Delete(id);
	}
}
