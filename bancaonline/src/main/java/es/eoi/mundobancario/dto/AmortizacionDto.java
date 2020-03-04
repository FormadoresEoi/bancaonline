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
public class AmortizacionDto {

	private int id;
	
	private String fecha;
	
	private double importe;
	
	private int id_prestamo;
}
