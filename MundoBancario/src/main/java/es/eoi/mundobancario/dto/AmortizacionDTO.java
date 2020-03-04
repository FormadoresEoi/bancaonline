package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmortizacionDTO {

	private int id;
	private Date fecha;
	private float importe;
}
