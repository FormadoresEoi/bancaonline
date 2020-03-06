package es.eoi.mundobancario.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDtoCliente {

	private int id;
	private String descripcion;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private double importe;
	private int plazo;
	private ClienteDto cliente;
	private List<AmortizacionDto> amortizacion;

}
