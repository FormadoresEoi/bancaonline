package es.eoi.mundobancario.dto;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDtoAmortizaciones {

	private int id;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Calendar fecha;
	private double importe;
	private int plazo;
	private List<AmortizacionDto> amortizacion;

}
