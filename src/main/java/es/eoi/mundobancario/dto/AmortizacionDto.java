package es.eoi.mundobancario.dto;

import lombok.Data;

/**
 * Amortización DTO.
 * =================
 *
 * DTO for the Amortizacion entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class AmortizacionDto {
    private int id;
    private int prestamosId;
    private String fecha;
    private double importe;
}
