package es.eoi.mundobancario.dto;

import lombok.Data;

@Data
public class CuentaReportDTO {
	
	private int numcuenta;
	private String alias;
	private double saldo;
	private ClienteDTO cliente;
}
