package es.eoi.mundobancario.dto;

import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovimientoDto {

	private int id;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Calendar fecha;
	private double importe;

}
