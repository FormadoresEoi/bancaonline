package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClienteDto {

	private int id;
	private String usuario;
	private String pass;
	private String nombre;
	private String email;

}
