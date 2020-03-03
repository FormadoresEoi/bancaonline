package es.eoi.mundobancario.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDto {

	int id;
	String descrpicon;
	Date fecha;
	float importe;
	int plazos;
	List<AmortizacionDto> amortizaciones;

}
