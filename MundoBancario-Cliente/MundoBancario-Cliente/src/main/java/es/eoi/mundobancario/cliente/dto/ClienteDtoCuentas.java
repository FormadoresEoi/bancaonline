package es.eoi.mundobancario.cliente.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClienteDtoCuentas {

	private String usuario;
	private String nombre;
	private String email;
	private List<CuentaDto> cuenta;
}
