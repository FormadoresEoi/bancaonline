package es.eoi.mundobancario.dto;

import es.eoi.mundobancario.entity.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CuentaDTO {

	private int numcuenta;
	private String alias;
	private double saldo;
	Cliente cliente;
	
	public CuentaDTO(int numcuenta) {
		super();
		this.numcuenta = numcuenta;
	}

}