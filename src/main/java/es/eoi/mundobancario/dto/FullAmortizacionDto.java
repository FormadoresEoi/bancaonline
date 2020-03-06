package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Full amortizacion DTO.
 * ======================
 *
 * Contains amortizacion relationships.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FullAmortizacionDto extends AmortizacionDto {
    private PrestamoDto prestamo;
}
