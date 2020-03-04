package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoNuevoDto {

	String descripcion;
	float importe;
	int plazos;

}
