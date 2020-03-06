package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Full cliente full cuenta DTO.
 * =============================
 *
 * Contains cliente and cuentas relationships.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FullClienteFullCuentaDto extends ClienteDto {
    private List<FullCuentaDto> cuentas;
}
