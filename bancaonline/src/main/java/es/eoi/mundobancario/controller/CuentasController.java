package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.eoi.mundobancario.dto.*;
import es.eoi.mundobancario.entity.*;
import es.eoi.mundobancario.enums.Tipos;
import es.eoi.mundobancario.service.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuentas")
public class CuentasController {

	private final ModelMapper model;	
	private final CuentaService cuentaService;
	private final ClienteService clienteService;
	private final PrestamoService prestamoService;
	private final MovimientoService movimientoService;
	private final AmortizacionService amortizacionService;
	

	@PostMapping
	public ResponseEntity<String> create(@RequestBody NewCuentaDto dto) {
		Optional<Cliente> cliente = clienteService.find(dto.getId_cliente());

		if (!cliente.isPresent())
			return new ResponseEntity<String>("fallo",HttpStatus.BAD_REQUEST);
		else {
			Cuenta cuenta = model.map(dto, Cuenta.class);
			cuenta.setCliente(cliente.get());
			cuentaService.create(cuenta);
		}
		return new ResponseEntity<String>("cuenta creada con exito", HttpStatus.OK);
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
		List<CuentaBasicaDto> cuentas = cuentaService.findAll()
				.stream()
				.map(c -> model.map(c, CuentaBasicaDto.class))
				.collect(Collectors.toList());

		return new ResponseEntity<List<CuentaBasicaDto>>(cuentas, HttpStatus.FOUND);
	}
	
	@GetMapping("/deudoras")
	public ResponseEntity<List<CuentaBasicaDto>> findAllNegative() {
		List<CuentaBasicaDto> cuentas = cuentaService.findBySaldoLessThan(0.0)
				.stream()
				.map(c -> model.map(c, CuentaBasicaDto.class))
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
	

	@PostMapping("/{id}/prestamos")
	public ResponseEntity<String> createPrestamo(@RequestBody NewPrestamoDto dto) {
		Optional<Cuenta> cuenta = cuentaService.find(dto.getId_cuenta());
		if (!cuenta.isPresent() || dto.getPlazos() == 0)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		else {
			CuentaBasicaDto cuentaDto = model.map(cuenta.get(), CuentaBasicaDto.class);
			PrestamoDto prestamoDto = model.map(dto, PrestamoDto.class);
			prestamoDto.setCuenta(cuentaDto);
			Prestamo prestamo = model.map(prestamoDto, Prestamo.class);
			
			prestamo.setAmortizaciones(amortizacionService.calcularAmortizaciones(prestamo));
			prestamoService.create(prestamo);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}/prestamos")
	public ResponseEntity<List<PrestamoDto>> findPrestamos(@PathVariable int id) {		
		List<PrestamoDto> prestamos = prestamoService.findAllByCuenta(id)
				.stream()
                .map(c -> model.map(c, PrestamoDto.class))
                .collect(Collectors.toList());

		return new ResponseEntity<List<PrestamoDto>>(prestamos, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}/prestamosAmortizados")
	public ResponseEntity<List<NewPrestamoDto>> findPrestamosAmortizados(@PathVariable int id) {		
		List<NewPrestamoDto> prestamos = prestamoService.findAllByCuentaIdAmortizados(id)
				.stream()
                .map(c -> model.map(c, NewPrestamoDto.class))
                .collect(Collectors.toList());

		return new ResponseEntity<List<NewPrestamoDto>>(prestamos, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}/prestamoVivos")
	public ResponseEntity<List<PrestamoDto>> findPrestamosVivos(@PathVariable int id) {		
		List<PrestamoDto> prestamos = prestamoService.findAllByCuentaIdVivos(id)
				.stream()
                .map(c -> model.map(c, PrestamoDto.class))
                .collect(Collectors.toList());

		return new ResponseEntity<List<PrestamoDto>>(prestamos, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/ingresos")
	public ResponseEntity<String> createIngreso(@RequestBody NewMovimientoDto dto) {
		Optional<Cuenta> cuenta = cuentaService.find(dto.getCuenta());
		if (!cuenta.isPresent())
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		else {
			//CuentaBasicaDto cuentaDto = model.map(cuenta.get(), CuentaBasicaDto.class);
			MovimientoDto movimientoDto = model.map(dto, MovimientoDto.class);
			Movimiento movimiento = model.map(movimientoDto, Movimiento.class);

			Tipos tipo = Tipos.Ingreso;
			TipoMovimiento tipoMov = new TipoMovimiento();
			tipoMov.setId(tipo.getEnumCode());
			tipoMov.setTipo(tipo.getEnumDesc());
			
			movimientoService.RealizarMovimiento(
					movimiento.getImporte(), 
					movimiento.getCuenta(), 
					movimiento.getFecha(), 
					movimiento.getDescripcion(), tipoMov);
			
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping("/{id}/pagos")
	public ResponseEntity<String> createPagos(@RequestBody NewMovimientoDto dto) {
		Optional<Cuenta> cuenta = cuentaService.find(dto.getCuenta());
		if (!cuenta.isPresent())
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		else {
			//CuentaBasicaDto cuentaDto = model.map(cuenta.get(), CuentaBasicaDto.class);
			MovimientoDto movimientoDto = model.map(dto, MovimientoDto.class);
			Movimiento movimiento = model.map(movimientoDto, Movimiento.class);

			Tipos tipo = Tipos.Pago;
			TipoMovimiento tipoMov = new TipoMovimiento();
			tipoMov.setId(tipo.getEnumCode());
			tipoMov.setTipo(tipo.getEnumDesc());
			
			movimientoService.RealizarMovimiento(
					movimiento.getImporte(), 
					movimiento.getCuenta(), 
					movimiento.getFecha(), 
					movimiento.getDescripcion(), tipoMov);
			
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping("/ejecutarAmortizacionesDiarias")
	public ResponseEntity<String> ejecutarAmortizacionesDiarias() {
		
			amortizacionService.ejecutarAmortizacionesDiarias();
			return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}/movimientos")
	public ResponseEntity<List<MovimientoDto>> findAllMovimientosById(@PathVariable int id) {
		List<MovimientoDto> movimientos =  movimientoService.findAllByCuentaId(id) 
				.stream()
                .map(c -> model.map(c, MovimientoDto.class))
                .collect(Collectors.toList());
		
		return new ResponseEntity<List<MovimientoDto>>(movimientos, HttpStatus.FOUND);
	}
	
}
