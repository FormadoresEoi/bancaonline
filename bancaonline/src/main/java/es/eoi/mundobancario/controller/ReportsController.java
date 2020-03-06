package es.eoi.mundobancario.controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteCuentaDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.PrestamoService;
import lombok.var;


@RestController
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CuentaService cuentaService;
	
	@Autowired
	private PrestamoService prestamoService;
	
	@Autowired
	private ModelMapper model;

	
	@GetMapping(value = "/clientes/{id}")
	public ResponseEntity<ClienteCuentaDto> findById(@PathVariable int id) {
		Optional<Cliente> cliente = clienteService.find(id);
		if (!cliente.isPresent())
			return new ResponseEntity<ClienteCuentaDto>(HttpStatus.NOT_FOUND);
		ClienteCuentaDto dto = model.map(cliente.get(), ClienteCuentaDto.class);
		return new ResponseEntity<ClienteCuentaDto>(dto, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/clientes/{id}")
    public ResponseEntity<InputStreamResource> clientesReport() {
		
		Optional<Cliente> cliente = clienteService.find(1); ///////////////////////////////////TODO
		ClienteDto dto = model.map(cliente.get(), ClienteDto.class);
		
        ByteArrayInputStream bis = GeneratePdfReport.clientesReport(dto);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=clientesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("/prestamos/{id}")
	public ResponseEntity<PrestamoDto> findPrestamos(@PathVariable int id) {		
		Optional<Prestamo> prestamo = prestamoService.findById(id);
		if (!prestamo.isPresent())
			return new ResponseEntity<PrestamoDto>(HttpStatus.NOT_FOUND);
		PrestamoDto dto = model.map(prestamo.get(), PrestamoDto.class);
		return new ResponseEntity<PrestamoDto>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/prestamosVivos")
	public ResponseEntity<List<PrestamoDto>> findPrestamosVivos() {		
		List<PrestamoDto> prestamos = prestamoService.findAllVivos()
				.stream()
                .map(c -> model.map(c, PrestamoDto.class))
                .collect(Collectors.toList());

		return new ResponseEntity<List<PrestamoDto>>(prestamos, HttpStatus.OK);
	}

	
}