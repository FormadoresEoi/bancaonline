package es.eoi.mundobancario.dto;

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
public class NewPrestamoDto {

	private int id;
	
	private String descripcion;
	
	private String fecha;
	
	private double importe;
	
	private int plazos;
	
	private int id_cuenta;
	
}
