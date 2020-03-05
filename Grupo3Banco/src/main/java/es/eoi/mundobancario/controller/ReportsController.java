package es.eoi.mundobancario.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteReportDTO;
import es.eoi.mundobancario.dto.CuentaReportDTO;
import es.eoi.mundobancario.service.ClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/reports")
public class ReportsController {
	
	private final ClienteService service;
	private final ModelMapper mapper;
	
	//Falta hacerlos xD
	@GetMapping("clientes/{id}")
	public List<ClienteReportDTO> getClientes(@PathVariable int id) {
        return mapper.map(service.findById(id).getCuentas(),new TypeToken<List<ClienteReportDTO>>() {
        }.getType());
    }
	
	@PostMapping("clientes/{id}")
	public List<ClienteReportDTO> postClientes(@PathVariable int id) {
        return mapper.map(service.findById(id).getCuentas(), new TypeToken<List<CuentaReportDTO>>() {
        }.getType());
    }
	
	@GetMapping("prestamos/{id}")
	public List<ClienteReportDTO> getPrestamos(@PathVariable int id) {
        return mapper.map(service.findById(id).getCuentas(),new TypeToken<List<ClienteReportDTO>>() {
        }.getType());
    }
	
	@PostMapping("prestamos/{id}")
	public List<ClienteReportDTO> postPrestamos(@PathVariable int id) {
        return mapper.map(service.findById(id).getCuentas(), new TypeToken<List<CuentaReportDTO>>() {
        }.getType());
    }
	
	@GetMapping("prestamos/Vivos")
	public List<ClienteReportDTO> getPrestamosVivos(@PathVariable int id) {
        return mapper.map(service.findById(id).getCuentas(),new TypeToken<List<ClienteReportDTO>>() {
        }.getType());
    }
	
	@GetMapping("prestamos/Amortizados")
	public List<ClienteReportDTO> getPrestamosAmortizados(@PathVariable int id) {
        return mapper.map(service.findById(id).getCuentas(),new TypeToken<List<ClienteReportDTO>>() {
        }.getType());
    }
}