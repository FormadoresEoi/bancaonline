package es.eoi.mundobancario.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import es.eoi.mundobancario.dto.AmortizacionDto;
import es.eoi.mundobancario.dto.ClienteDtoCuentasMovimientos;
import es.eoi.mundobancario.dto.CuentaDtoMovimientos;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDtoCliente;

@Component
public class Reports {
	
	@Autowired
	DtoConstructor dtoConstructor;
	
	public void printCliente(ClienteDtoCuentasMovimientos cliente) {
		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(
						"C:\\Users\\formacion\\git\\finalProyect\\bancaonline\\Recursos\\EOI_BANK_CLIENTE_000.pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No se encontró el fichero para generar el pdf" + fileNotFoundException);
			}
			document.open();

			Chunk chunk = new Chunk("Cliente " + cliente.getUsuario());
			Chapter chapter = new Chapter(new Paragraph(chunk), 1);
			chapter.setNumberDepth(0);
			document.add(chapter);
			for (CuentaDtoMovimientos cuenta : cliente.getCuenta()) {
				Paragraph paragraph = new Paragraph("Cuenta " + cuenta.getAlias());
				for (MovimientoDto movimiento : cuenta.getMovimiento()) {
					if (movimiento.getDescripcion().equals("INGRESO")
							|| movimiento.getDescripcion().equals("PRESTAMO")) {
						paragraph.add(new Chunk(movimiento.toString()).setBackground(BaseColor.GREEN));
					} else {
						paragraph.add(new Chunk(movimiento.toString()).setBackground(BaseColor.RED));
					}
					document.add(paragraph);
				}
			}
			document.close();
			System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
		} catch (com.itextpdf.text.DocumentException documentException) {
			System.out.println(
					"Se ha producido un error al generar un documento: " + documentException);
		}
	}
	
	public void printPrestamos(PrestamoDtoCliente prestamo) {
		
		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(
						"C:\\Javi\\repositorio\\ProyectoFinal\\bancaonline\\Recursos\\EOI_BANK_PRESTAMO_000.pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("No se encontró el fichero para generar el pdf" + fileNotFoundException);
			}
			document.open();

			Chunk chunk = new Chunk("Cliente " + prestamo.getCliente().getUsuario());
			Chapter chapter = new Chapter(new Paragraph(chunk), 1);
			chapter.setNumberDepth(0);
			document.add(chapter);
			for (AmortizacionDto amortizacion : prestamo.getAmortizacion()) {
				chapter.add(new Paragraph(amortizacion.toString()));
				document.add(chapter);
			}
			document.close();
			System.out.println("¡Se ha generado tu hoja PDF!");
		} catch (com.itextpdf.text.DocumentException documentException) {
			System.out.println(
					"Se ha producido un error al generar un documento: " + documentException);
		}
	}
}
