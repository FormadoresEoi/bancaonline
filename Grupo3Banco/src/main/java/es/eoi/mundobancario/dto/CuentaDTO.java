package es.eoi.mundobancario.dto;

import es.eoi.mundobancario.entity.Cliente;
import lombok.Data;

@Data

public class CuentaDTO {

	private int numcuenta;
	private String alias;
	private double saldo;
	Cliente cliente;
	
//	public CuentaDTO(int numcuenta) {
//		super();
//		this.numcuenta = numcuenta;
//	}

}