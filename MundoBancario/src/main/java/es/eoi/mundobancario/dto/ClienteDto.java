package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class ClienteDto {

	private int id;
	private String usuario;
	private String pass;
	private String nombre;
	private String email;

}
