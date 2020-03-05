package es.eoi.mundobancario.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDtoCliente {

	private int id;
	private String descripcion;
	private Date fecha;
	private double importe;
	private int plazo;
	private ClienteDto cliente;
	private List<AmortizacionDto> amortizacion;

}
