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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteBasicoDto;
import es.eoi.mundobancario.dto.ClienteDto;
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

		if (!cliente.isPresent())
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
		Optional<Cuenta> cuenta = cuentaService.find(id);
		if (!cuenta.isPresent())
			return new ResponseEntity<CuentaBasicaDto>(HttpStatus.NOT_FOUND);
		CuentaBasicaDto dto = model.map(cuenta.get(), CuentaBasicaDto.class);
		return new ResponseEntity<CuentaBasicaDto>(dto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CuentaBasicaDto>> findAll() {
		List<CuentaBasicaDto> cuentas = cuentaService.findAll().stream().map(c -> model.map(c, CuentaBasicaDto.class))
				.collect(Collectors.toList());

		return new ResponseEntity<List<CuentaBasicaDto>>(cuentas, HttpStatus.FOUND);
	}
	
	
	@GetMapping("/deudoras")
	public ResponseEntity<List<CuentaBasicaDto>> findAllNegative() {
		//DOUBLE
		List<CuentaBasicaDto> cuentas = cuentaService.findBySaldoLessThan(0.0).stream().map(c -> model.map(c, CuentaBasicaDto.class))
				.collect(Collectors.toList());

		return new ResponseEntity<List<CuentaBasicaDto>>(cuentas, HttpStatus.FOUND);
	}


	@PutMapping("/{num_cuenta}")
	public ResponseEntity<CuentaBasicaDto> update(@PathVariable int num_cuenta, @RequestParam String alias) {
		Cuenta cuenta = cuentaService.find(num_cuenta).get();
		cuenta.setAlias(alias);
		cuentaService.update(cuenta);
		CuentaBasicaDto modifyCuenta = model.map(cuenta, CuentaBasicaDto.class);
		return new ResponseEntity<CuentaBasicaDto>(modifyCuenta, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}/movimientos")
	public ResponseEntity<CuentaDto> findAllMovimientosById(@RequestParam int num_cuenta) {
		CuentaDto cuenta = model.map(cuentaService.find(num_cuenta).get(), CuentaDto.class);

		return new ResponseEntity<CuentaDto>(cuenta, HttpStatus.OK);
	}
	
	

}
