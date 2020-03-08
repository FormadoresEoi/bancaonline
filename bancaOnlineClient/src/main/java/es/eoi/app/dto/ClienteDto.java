package es.eoi.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDto implements Serializable{
	
	private int id;
	
	private String nombre;
	
	private String usuario;
	
	private String email;
	
	private List<NewCuentaDto> cuentas;
}
