package es.eoi.mundobancario.controller;

import static es.eoi.mundobancario.utils.DtoConverter.toCuentaConMovimientosDtoList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.eoi.mundobancario.dto.CuentaConMovimientosDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
@RestController
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	ClienteService service;
	CuentaService cuentaService;
	@GetMapping(value = "/clientes/{id}")
	public List<CuentaConMovimientosDto> getClientesWithCuentasAndMovimientos(@PathVariable("id") Integer id) {
		return toCuentaConMovimientosDtoList(service.getById(id).getCuentas());
	}

	@PostMapping(value = "/clientes/{id}")
	public void getClientesWithCuentasAndMovimientosPDF(@PathVariable("id") Integer id) {
		Document document = new Document();
		Cliente cliente = service.getById(id);
		try {
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/formacion/Documents/ClientesConCuentas.pdf"));
		
		document.open();
		document.add(new Paragraph(Integer.toString((cliente.getId()))));
		document.add(new Paragraph(cliente.getUsuario()));
		document.add(new Paragraph(cliente.getPass()));
		document.add(new Paragraph(cliente.getNombre()));
		document.add(new Paragraph(cliente.getEmail()));
		for (Cuenta cuenta : cliente.getCuentas()) {
			document.add(new Paragraph(Integer.toString((cuenta.getNum_cuenta()))));
			document.add(new Paragraph(cuenta.getAlias()));
			document.add(new Paragraph(Float.toString((cuenta.getSaldo()))));
			for (Movimiento movimiento : cuenta.getMovimientos()) {
				document.add(new Paragraph(Integer.toString((movimiento.getId()))));
				document.add(new Paragraph(movimiento.getDescripcion()));
				document.add(new Paragraph((new SimpleDateFormat("dd-MM-yyyy")).format(movimiento.getFecha().getTime())));
				document.add(new Paragraph(Float.toString((movimiento.getImporte()))));				
			}
		}
		document.close();
		writer.close();
		
		} catch  (DocumentException e) {
	         e.printStackTrace();
	    } catch (FileNotFoundException e) {
	         e.printStackTrace();
	    }
		
	}

	
}
