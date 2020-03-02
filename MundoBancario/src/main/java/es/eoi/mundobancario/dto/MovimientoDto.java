package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDto {

	int id;
	String descripcion;
	Date fecha;
	float importe;
	int tipo;
	int cuenta;

}
