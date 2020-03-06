package es.eoi.mundobancario.cliente.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDtoPrestamos {

	private int numeroCuenta;
	private String alias;
	private double saldo;
	private List<PrestamoDto> prestamo;
}
