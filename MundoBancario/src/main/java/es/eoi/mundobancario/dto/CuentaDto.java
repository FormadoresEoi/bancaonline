package es.eoi.mundobancario.dto;

import lombok.Data;

@Data
public class CuentaDto {

	private int numeroCuenta;
	private String alias;
	private double saldo;

}
