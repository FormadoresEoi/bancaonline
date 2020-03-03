package es.eoi.mundobancario.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.Service.ClienteService;
import es.eoi.mundobancario.Service.PrestamoService;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Prestamo;

@RestController
@RequestMapping(value="/reports")
public class ReportsController {
	
	@Autowired
	ClienteService clienteService;
	@Autowired
	PrestamoService prestamoService;
	@Autowired
	ModelMapper modelMapper;
	
	private ClienteDto toClienteDto(Cliente cliente) {
		ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
		return clienteDto;
	}
	
	private PrestamoDto toPrestamoDto(Prestamo prestamo) {
		PrestamoDto prestamoDto = modelMapper.map(prestamo, PrestamoDto.class);
		return prestamoDto;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/clientes/{id}")
	public ClienteDto findClientes(@PathVariable int id) {
		return toClienteDto(clienteService.findById(id));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/clientes/{id}")
	public void printDatos(@PathVariable int id) {
		 //TODO pdfprint.print(findClientes(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/prestamos/{id}")
	public ClienteDto findPrestamos(@PathVariable int id) {
		return toClienteDto(clienteService.findPrestamos(id));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/prestamos/{id}")
	public void printPrestamos(@PathVariable int id) {
		 //TODO pdfprint.print(findPrestamos(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/prestamos/{id}")
	public List<PrestamoDto> findPrestamosVivos(@PathVariable int id) {
		List<PrestamoDto> prestamos = new ArrayList<PrestamoDto>();
		for (Prestamo prestamo : prestamoService.findAllVivos()) {
			prestamos.add(toPrestamoDto(prestamo));
		}
		return prestamos;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/prestamos/{id}")
	public List<PrestamoDto> findPrestamosAmortizados(@PathVariable int id) {
		List<PrestamoDto> prestamos = new ArrayList<PrestamoDto>();
		for (Prestamo prestamo : prestamoService.findAllAmortizados()) {
			prestamos.add(toPrestamoDto(prestamo));
		}
		return prestamos;
	}
}
