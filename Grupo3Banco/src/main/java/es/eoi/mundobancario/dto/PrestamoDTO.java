package es.eoi.mundobancario.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PrestamoDTO {
	private int id;
	private String descripcion;
	private Date fecha;
	private double importe;
	private int plazos;
	private List<AmortizacionDTO> amortizaciones;
}
