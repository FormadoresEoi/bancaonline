package es.eoi.mundobancario.dto;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDTOAmort {

	private int id;
	private String descripcion;
	private Date fecha;
	private float importe;
	private int plazos;
	private List<AmortizacionDTO> listAmort;

}
