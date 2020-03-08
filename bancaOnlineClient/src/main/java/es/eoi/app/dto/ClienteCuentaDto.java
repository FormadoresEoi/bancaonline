package es.eoi.app.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@Data
public class ClienteCuentaDto extends ClienteBasicoDto implements Serializable{
	
	private List<CuentaDto> cuentas;

}
