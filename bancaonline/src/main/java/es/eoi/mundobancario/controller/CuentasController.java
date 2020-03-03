package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.CuentaBasicaDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.NewCuentaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentasController {
	
	@Autowired
	private CuentaService cuentaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ModelMapper model;
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody NewCuentaDto dto) {
		Optional<Cliente> cliente = clienteService.find(dto.getId_cliente());
		
		if(!cliente.isPresent())
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		else {
			Cuenta cuenta = model.map(dto, Cuenta.class);
			cuenta.setCliente(cliente.get());
			cuentaService.create(cuenta);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CuentaBasicaDto> find(@PathVariable int id) {
		CuentaBasicaDto cuenta = model.map(cuentaService.find(id), CuentaBasicaDto.class);
		if(cuenta == null)
			return new ResponseEntity<CuentaBasicaDto>(HttpStatus.NOT_FOUND);
		CuentaBasicaDto dto = model.map(cuenta, CuentaBasicaDto.class);
		return new ResponseEntity<CuentaBasicaDto>(dto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CuentaBasicaDto>> findAll(){
		List<CuentaBasicaDto> cuentas = cuentaService.findAll()
				.stream()
				.map(c -> model.map(c, CuentaBasicaDto.class))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<CuentaBasicaDto>>(cuentas, HttpStatus.FOUND);
	}

	@PutMapping("/{id}")
	public void update(@RequestBody CuentaDto dto) {
		Cuenta cuenta = model.map(dto, Cuenta.class);
		cuentaService.update(cuenta);
	}

}
