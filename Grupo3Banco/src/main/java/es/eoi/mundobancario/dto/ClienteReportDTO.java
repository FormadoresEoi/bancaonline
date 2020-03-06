package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClienteReportDTO {
	
	private ClienteLoginDTO cliente;
	private CuentaReportDTO cuenta;
	
	
}