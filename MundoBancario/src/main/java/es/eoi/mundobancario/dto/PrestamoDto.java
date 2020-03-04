package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class PrestamoDto {

	private int id;
	private String descripcion;
	private Date fecha;
	private double importe;
	private int plazo;

}
