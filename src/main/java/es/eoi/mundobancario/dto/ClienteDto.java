package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Cliente DTO. ============
 *
 * DTO for the Cliente entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
@Getter
@Setter

public class ClienteDto {
	private int id;
	private String usuario;
	private String nombre;
	private String email;
}
