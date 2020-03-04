package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClienteReportDTO {
	
	private int id;
	private String usuario;
	private String email;
	private String nombre;
	private List<CuentaDTO> cuentas;
	
}