package es.eoi.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewCuentaDto {

	private int numCuenta;
	
	private String alias;
	
	private double saldo;
	
	private int id_cliente;
}
