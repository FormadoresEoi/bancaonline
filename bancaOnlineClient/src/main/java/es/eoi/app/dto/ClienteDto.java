package es.eoi.app.dto;

import java.util.List;

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
public class ClienteDto {
	
	private int id;
	
	private String nombre;
	
	private String usuario;
	
	private String email;
	
	private List<NewCuentaDto> cuentas;
}
