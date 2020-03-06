package es.eoi.mundobancario.dto;

import es.eoi.mundobancario.entity.TiposMovimiento;
import lombok.Data;

@Data
public class MovimientoDTO {
	
	private int id;
	private String descripcion;
	private double importe;
	private TiposMovimientoDTO tiposmovimiento;
}