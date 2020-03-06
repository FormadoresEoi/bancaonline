package es.eoi.cliente.dto;

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
