package es.eoi.cliente.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CuentaConMovimientosDto {

	int num_cuenta;
	String alias;
	float saldo;
	List<MovimientoDto> movimiento;
	
}
