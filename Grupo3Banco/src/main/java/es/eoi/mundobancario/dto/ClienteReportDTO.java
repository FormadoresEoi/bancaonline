package es.eoi.mundobancario.dto;

import lombok.Data;

@Data
public class ClienteReportDTO {
	
	private ClienteLoginDTO cliente;
	private CuentaReportDTO cuenta;
	
	
}