package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AmortizacionDto {

	private int id;
	private int idPrestamo;
	private Date fecha;
	private double importe;

}
