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
public class AmortizacionDto implements Serializable{

	private int id;
	
	private String fecha;
	
	private double importe;
	
	private int id_prestamo;
	
	private String estado;
}
