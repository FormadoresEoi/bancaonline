package es.eoi.mundobancario.dto;

import es.eoi.mundobancario.entity.TiposMovimiento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoDTO {
	
	private int id;
	private String descripcion;
	private double importe;
	private TiposMovimientoDTO tiposmovimiento;
}