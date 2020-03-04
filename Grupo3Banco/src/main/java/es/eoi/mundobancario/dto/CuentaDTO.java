package es.eoi.mundobancario.dto;

import lombok.Data;

@Data
public class CuentaDTO {

	private int numcuenta;
	private String alias;
	private double saldo;
	private ClienteDTO cliente;
	

	//public CuentaDTO(int numcuenta) {
	//	super();
	//	this.numcuenta = numcuenta;
	//}
}