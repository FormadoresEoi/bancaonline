package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Full cliente DTO.
 * =================
 *
 * Contains cliente relationships.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FullClienteDto extends ClienteDto {
    private List<CuentaDto> cuentas;
}
