package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDTO {

	private String descripcion;
	private float importe;
	private int plazos;

}
