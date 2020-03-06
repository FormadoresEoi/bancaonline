package es.eoi.mundobancario.controller;

import static es.eoi.mundobancario.utils.DtoConverter.toCuentaConMovimientosDtoList;
import static es.eoi.mundobancario.utils.DtoConverter.toPrestamoConClienteDto;
import static es.eoi.mundobancario.utils.DtoConverter.toPrestamoDtoList;

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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.eoi.mundobancario.dto.CuentaConMovimientosDto;
import es.eoi.mundobancario.dto.PrestamoConClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.PrestamoService;

import es.eoi.mundobancario.utils.SendEmailAttachment;

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
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("EOI_BANK_CLIENTE_" + id.toString() + ".pdf"));
			document.open();
			document.add(new Paragraph("Cliente: " + cliente.getNombre()));
			document.add(new Paragraph("Correo electrónico: " + cliente.getEmail() + "\n\n"));
			for (Cuenta cuenta : cliente.getCuentas()) {
				document.add(new Paragraph("Cuenta: " + cuenta.getAlias() + " con un saldo de "
						+ Float.toString(cuenta.getSaldo()) + " €\n\n"));
				for (Movimiento movimiento : cuenta.getMovimientos()) {
					Paragraph entrada = new Paragraph();
					entrada.add(new Chunk("       "));
					entrada.add(
							new Chunk((new SimpleDateFormat("dd-MM-yyyy")).format(movimiento.getFecha().getTime())));
					entrada.add(new Chunk(" | " + movimiento.getDescripcion() + " | "));
					if (movimiento.getTipo().getTipo().equals("INGRESO")
							|| movimiento.getTipo().getTipo().equals("PRÉSTAMO"))
						entrada.add(new Chunk(Float.toString(movimiento.getImporte()) + " €",
								new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.GREEN)));
					else
						entrada.add(new Chunk(Float.toString(-1 * movimiento.getImporte())+ " €",
								new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED)));
					entrada.add(new Chunk(" | " + movimiento.getTipo().getTipo()));
					document.add(entrada);
				}
			}
			document.close();
			writer.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		(new SendEmailAttachment()).Enviar("EOI_BANK_CLIENTE_" + id.toString() + ".pdf",
				cliente.getEmail());
	}

	@Autowired
	PrestamoService prestamoService;

	@GetMapping("/prestamos/{id}")
	public PrestamoConClienteDto getPrestamoConClienteReport(@PathVariable Integer id) {
		return toPrestamoConClienteDto(prestamoService.getById(id));
	}

	@PostMapping("/prestamos/{id}")
	public boolean postPdfPrestamoConClienteReport(@PathVariable Integer id) {
		Prestamo prestamo = prestamoService.getById(id);
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("EOI_BANK_PRESTAMO_" + id.toString() + ".pdf"));
			document.open();
			document.add(new Paragraph("Cliente: " + prestamo.getCuenta().getCliente().getNombre()));
			document.add(new Paragraph("Correo electrónico: " + prestamo.getCuenta().getCliente().getEmail() + "\n"));
			document.add(new Paragraph("\nPréstamo: " + prestamo.getDescripcion()));
			document.add(new Paragraph("\n" + prestamo.getPlazos() + " amortizaciones: "));
			for (Amortizacion amortizacion : prestamo.getAmortizaciones()) {
				document.add(new Paragraph(
						"       " + (new SimpleDateFormat("dd-MM-yyyy")).format(amortizacion.getFecha().getTime())
								+ " con un importe de " + amortizacion.getImporte() + "€"));

			}
			document.close();
			writer.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		(new SendEmailAttachment()).Enviar("EOI_BANK_PRESTAMO_" + id.toString() + ".pdf",
				prestamo.getCuenta().getCliente().getEmail());
		return true;
	}

	@GetMapping(value = "/prestamosVivos")
	public List<PrestamoDto> getPrestamosVivosReport() {
		return toPrestamoDtoList(prestamoService.getPrestamosVivosAll());
	}

	@GetMapping(value = "/prestamosAmortizados")
	public List<PrestamoDto> getPrestamosAmortizadosReport() {
		return toPrestamoDtoList(prestamoService.getPrestamosAmortizados());
	}
}
