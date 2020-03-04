package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaConClienteDto {

	int num_cuenta;
	String alias;
	float saldo;
	ClienteDto cliente;

}
