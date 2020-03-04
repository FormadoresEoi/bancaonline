package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CuentaDtoCliente {

	private int numeroCuenta;
	private String alias;
	private double saldo;
	private ClienteDto cliente;
}
