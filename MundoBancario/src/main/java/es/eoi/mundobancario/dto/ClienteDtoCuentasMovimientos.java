package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteDtoCuentasMovimientos {

	private String usuario;
	private String nombre;
	private String email;
	private List<CuentaDtoMovimientos> cuenta;
}
