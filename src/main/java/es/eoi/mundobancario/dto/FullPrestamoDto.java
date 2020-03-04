package es.eoi.mundobancario.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Full prestamo DTO.
 * ==================
 *
 * Contains prestamo relationships.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FullPrestamoDto extends PrestamoDto {
    private List<AmortizacionDto> amortizaciones;
    private CuentaDto             cuenta;
}
