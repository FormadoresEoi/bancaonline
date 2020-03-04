package es.eoi.mundobancario.dto;

import java.util.List;

import es.eoi.mundobancario.entity.Movimiento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDtoMovimientos {
	
	private int numeroCuenta;
	private String alias;
	private double saldo;
	private List<Movimiento> movimiento;

}
