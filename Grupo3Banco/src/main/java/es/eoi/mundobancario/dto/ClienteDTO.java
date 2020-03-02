package es.eoi.mundobancario.dto;

import lombok.Data;

@Data
public class ClienteDTO {
	private int id;
	private String usuario;
	private String nombre;
	private String email;
}
