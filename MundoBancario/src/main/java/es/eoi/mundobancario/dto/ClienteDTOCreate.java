package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClienteDTOCreate {

	private String usuario;
	private String nombre;
	private String email;
	private String pass;
}
