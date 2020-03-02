package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDto {

	int id;
	String descrpicon;
	Date fecha;
	float importe;
	int plazos;
	int cuenta;

}
