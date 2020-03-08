package es.eoi.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovimientoDto implements Serializable{

	private int id;
	
	private String descripcion;
	
	private String fecha;
	
	private double importe;
	
	private int cuenta;
	
	private TipoMovimientoDto tipo;
	
}
