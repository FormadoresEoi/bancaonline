package es.eoi.mundobancario.dto;

import java.sql.Timestamp;
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
	
	private Timestamp fecha;
	
	private double importe;
	
	private int plazos;
	
	private CuentaDto cuentaPres;
	
	private List<AmortizacionDto> amortizaciones;
}
