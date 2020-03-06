package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CuentaClienteDTO {
	
	private int numcuenta;
	private String alias;
	private double saldo;
	private ClienteDTO cliente;

}
