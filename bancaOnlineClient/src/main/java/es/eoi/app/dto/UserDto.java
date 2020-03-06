package es.eoi.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
		
	private int id;
	
	private String nombre;

	private String usuario;
	
	private String pass;
	
	private String email;
	
	

}
