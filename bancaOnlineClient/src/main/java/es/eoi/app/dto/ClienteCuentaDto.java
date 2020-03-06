package es.eoi.app.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClienteCuentaDto extends ClienteBasicoDto{
	
	private List<CuentaDto> cuentas;

}
