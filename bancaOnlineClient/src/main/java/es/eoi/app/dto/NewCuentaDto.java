package es.eoi.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewCuentaDto implements Serializable{

	private int numCuenta;
	
	private String alias;
	
	private double saldo;
	
	private int id_cliente;
}
