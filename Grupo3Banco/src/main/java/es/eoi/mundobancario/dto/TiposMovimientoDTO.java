package es.eoi.mundobancario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiposMovimientoDTO {

	private int id;
	private String tipo;
}