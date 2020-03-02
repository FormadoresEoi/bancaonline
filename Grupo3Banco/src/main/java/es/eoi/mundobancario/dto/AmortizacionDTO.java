package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AmortizacionDTO {
	private int id;
	private Date fecha;
	private int importe;
}