package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AmortizacionDto {

	private int id;
	private int idPrestamo;
	private Date fecha;
	private double importe;

}
