package es.eoi.mundobancario.dto;

import lombok.Data;

@Data
public class MovimientoDTO {
	
	private int id;
	private String descripcion;
	private double importe;
	private TiposMovimientoDTO tiposmovimiento;
}