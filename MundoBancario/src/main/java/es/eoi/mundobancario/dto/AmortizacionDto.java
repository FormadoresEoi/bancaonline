package es.eoi.mundobancario.dto;

import java.sql.Date;

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
	private Date fecha;
	private double importe;

}
