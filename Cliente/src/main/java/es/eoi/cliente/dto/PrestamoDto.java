package es.eoi.cliente.dto;

import java.util.Calendar;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDto {

	int id;
	String descripcion;
	Calendar fecha;
	float importe;
	int plazos;
	List<AmortizacionDto> amortizaciones;

}
