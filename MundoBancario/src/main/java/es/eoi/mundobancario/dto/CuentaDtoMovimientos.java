package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDtoMovimientos {

	private int numeroCuenta;
	private String alias;
	private double saldo;
	private List<MovimientoDto> movimiento;

}
