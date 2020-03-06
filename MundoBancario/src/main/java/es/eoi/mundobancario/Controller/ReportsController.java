package es.eoi.mundobancario.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.Service.ClienteService;
import es.eoi.mundobancario.Service.PrestamoService;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteDtoCuentasMovimientos;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.dto.PrestamoDtoCliente;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.util.DtoConstructor;
import es.eoi.mundobancario.util.Reports;

@RestController
@RequestMapping(value = "/reports")
public class ReportsController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	PrestamoService prestamoService;
	@Autowired
	Reports printer;
	@Autowired
	DtoConstructor dtoConfigurator;

	@RequestMapping(method = RequestMethod.GET, value = "/clientes/{id}")
	public ClienteDtoCuentasMovimientos findClientes(@PathVariable int id) {
		return dtoConfigurator.ClienteDtoCuentasMovimientos(clienteService.findById(id));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/clientes/{id}")
	public void printDatos(@PathVariable int id) {
		printer.printCliente(findClientes(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/prestamos/{id}")
	public PrestamoDtoCliente findPrestamos(@PathVariable int id) {
		Prestamo prestamo = null;
		if(prestamoService.findById(id).isPresent())
			prestamo = prestamoService.findById(id).get();
		ClienteDto cliente = dtoConfigurator.toClienteDto(clienteService.findById(id));
		PrestamoDtoCliente prestamoDto = dtoConfigurator.toPrestamoDtoCliente(prestamo);
		prestamoDto.setCliente(cliente);
		return prestamoDto;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/prestamos/{id}")
	public void printPrestamos(@PathVariable int id) {
		printer.printPrestamos(findPrestamos(id));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/prestamosVivos/{id}")
	public List<PrestamoDto> findPrestamosVivos(@PathVariable int id) {
		List<PrestamoDto> prestamos = new ArrayList<PrestamoDto>();
		for (Prestamo prestamo : prestamoService.findAllVivos(id)) {
			prestamos.add(dtoConfigurator.toPrestamoDto(prestamo));
		}
		return prestamos;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/prestamosAmortizados/{id}")
	public List<PrestamoDto> findPrestamosAmortizados(@PathVariable int id) {
		List<PrestamoDto> prestamos = new ArrayList<PrestamoDto>();
		for (Prestamo prestamo : prestamoService.findAllAmortizados(id)) {
			prestamos.add(dtoConfigurator.toPrestamoDto(prestamo));
		}
		return prestamos;
	}
}
