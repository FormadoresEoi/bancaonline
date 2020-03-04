package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Full cuenta DTO.
 * ================
 *
 * Contains cuenta relationships.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FullCuentaDto extends CuentaDto {
    private ClienteDto cliente;
    private List<MovimientoDto> movimientos;
    private List<PrestamoDto> prestamos;
}
