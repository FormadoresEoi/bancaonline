package es.eoi.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteBasicoDto {
	
	private int id;
	
	private String nombre;
	
	private String usuario;
	
	private String email;
	
}
