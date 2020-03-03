package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmortizacionDto {

	int id;
	Date fecha;
	float importe;

}
