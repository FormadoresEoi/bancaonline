package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Full movimiento DTO.
 * ====================
 *
 * Contains movimiento relantionships.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FullMovimientoDto extends MovimientoDto {
    private CuentaDto cuenta;
    private TipoMovimientoDto tipo;
}
