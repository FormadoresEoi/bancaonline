package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoDTO {
	
	private int id;
	private String descripcion;
	private double importe;
}