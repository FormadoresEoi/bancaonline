package es.eoi.mundobancario.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.CuentaDTO;
import es.eoi.mundobancario.service.CuentaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuentas")
public class CuentasController {
	
	private final CuentaService service;
	private final ModelMapper mapper;
	
	// Devuelve todas las cuentas
	@GetMapping
	public List<CuentaDTO> getCuentas(){
		return mapper.map(service.listCuentas(),new TypeToken<List<CuentaDTO>>() {
		}.getType());
	}
	// Devuelve la cuenta solicitada.
	@GetMapping("{id}")
	public CuentaDTO getCuenta(@PathVariable int id) {
		return mapper.map(service.findById(id),CuentaDTO.class);
	}
	
//	@DeleteMapping
//	public void deleteCuenta(@RequestBody Cuenta cuenta) {
//		service.deleteCuenta(cuenta);
//	}

	//@PutMapping("{id}")
	//public void put(@PathVariable int id, String alias) {
	//	service.updateCuenta(service.findById(id), alias);
	//}

	//@GetMapping
	//public List<CuentaDTO> listCuentas() {
	//	List<Cuenta> listCuentas = service.listCuentas();
	//	List<CuentaDTO> dto = new ArrayList<CuentaDTO>();
	//	for (Cuenta cuenta : listCuentas) {
	//		CuentaDTO cuentadto = new CuentaDTO();
	//		cuentadto.setNumcuenta(cuenta.getNumcuenta());
	//		cuentadto.setAlias(cuenta.getAlias());
	//		cuentadto.setSaldo(cuenta.getSaldo());
	///		dto.add(cuentadto);
	//	}
	//	return dto;
	//}
}