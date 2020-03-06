package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NewPrestamoDTO {
	
	private int id;
	private int NUMCUENTA;
	private String descripcion;
	private Date fecha;
	private double importe;
	private int plazos;
}
