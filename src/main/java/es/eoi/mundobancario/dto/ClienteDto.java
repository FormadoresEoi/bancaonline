package es.eoi.mundobancario.dto;

import lombok.Data;

/**
 * Cliente DTO.
 * ============
 *
 * DTO for the Cliente entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class ClienteDto {
    private int id;
    private String usuario;
    private String nombre;
    private String email;
}
