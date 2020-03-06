package es.eoi.mundobancario.dto;

import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AmortizacionDto {

	private int id;
	private int idPrestamo;
	@Temporal(TemporalType.DATE)
	private Calendar fecha;
	private double importe;

}
