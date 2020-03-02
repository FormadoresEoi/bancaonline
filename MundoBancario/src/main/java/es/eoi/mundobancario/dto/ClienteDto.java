package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

	int id;
	String usuario;
	String pass;
	String nombre;
	String email;

}
