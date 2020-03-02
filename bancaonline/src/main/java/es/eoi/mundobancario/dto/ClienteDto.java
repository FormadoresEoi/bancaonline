package es.eoi.mundobancario.dto;

import java.util.List;

import javax.persistence.Column;

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
	
	@Column(name = "email")
	private String email;
	
	private List<CuentaDto> cuentas; 
}
