package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDto {

	int num_cuenta;
	String alias;
	float saldo;
	int cliente;

}
