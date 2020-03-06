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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cliente;
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
	public ResponseEntity<ClienteDto> findById(@PathVariable int id) {
		Optional<Cliente> cliente = clienteService.find(id);
		if (!cliente.isPresent())
			return new ResponseEntity<ClienteDto>(HttpStatus.NOT_FOUND);
		ClienteDto dto = model.map(cliente.get(), ClienteDto.class);
		return new ResponseEntity<ClienteDto>(dto, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
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
	
	
	
	
	//TODO falta que devuelva los datos del cliente
	@GetMapping("/prestamos/{id}")
	public ResponseEntity<List<PrestamoDto>> findPrestamos(@PathVariable int id) {		
		List<PrestamoDto> prestamos = prestamoService.findAllByCuenta(id)
				.stream()
                .map(c -> model.map(c, PrestamoDto.class))
                .collect(Collectors.toList());

		return new ResponseEntity<List<PrestamoDto>>(prestamos, HttpStatus.OK);
	}
	
	

	
}