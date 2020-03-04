package es.eoi.mundobancario.dto;

import lombok.Data;

@Data
public class ClienteLoginDTO {
	
	private int id;
	private String usuario;
	private String email;
	private String nombre;

}
