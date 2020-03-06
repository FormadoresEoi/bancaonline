package es.eoi.cliente.dto;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmortizacionDto {

	int id;
	Calendar fecha;
	float importe;

}
