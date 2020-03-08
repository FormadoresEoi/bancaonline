package es.eoi.app.dto;

import java.io.Serializable;
import java.util.List;

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
public class CuentaDto implements Serializable{

	private int numCuenta;
	
	private String alias;
	
	private double saldo;
	
	private ClienteBasicoDto cliente;
	
	private List<MovimientoDto> movimientos;
	
	private List<PrestamoDto> prestamos;
}
