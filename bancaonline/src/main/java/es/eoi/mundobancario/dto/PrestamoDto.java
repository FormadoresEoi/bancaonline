package es.eoi.mundobancario.dto;

import java.util.List;

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
public class PrestamoDto {

	private int id;
	
	private String descripcion;
	
	private String fecha;
	
	private double importe;
	
	private int plazos;
	
	private CuentaBasicaDto cuenta;
	
	private List<AmortizacionDto> amortizaciones;
}
