package es.eoi.mundobancario.cliente.dto;

import java.util.Date;

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
	private Date fecha;
	private double importe;

}
