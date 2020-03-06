package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDTOCreate {

	private String descripcion;
	private float importe;

}
