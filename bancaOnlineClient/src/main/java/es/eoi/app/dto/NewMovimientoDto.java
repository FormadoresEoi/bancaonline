package es.eoi.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewMovimientoDto {

	private int id;
	
	private String descripcion;
	
	private String fecha;
	
	private double importe;
	
	private int cuenta;
	
	private String tipo;
	
}
