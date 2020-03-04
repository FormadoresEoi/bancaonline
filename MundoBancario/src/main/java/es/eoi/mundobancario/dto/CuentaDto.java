package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class CuentaDto {

	private int numeroCuenta;
	private String alias;
	private double saldo;

}
