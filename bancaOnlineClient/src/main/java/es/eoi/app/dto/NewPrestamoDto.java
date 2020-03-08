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
public class NewPrestamoDto implements Serializable{

	private int id;
	
	private String descripcion;
	
	private String fecha;
	
	private double importe;
	
	private int plazos;
	
	private int id_cuenta;
	
}
