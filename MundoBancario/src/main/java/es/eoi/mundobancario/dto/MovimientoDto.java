package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MovimientoDto {

	private int id;
	private String descripcion;
	private Date fecha;
	private double importe;

}
