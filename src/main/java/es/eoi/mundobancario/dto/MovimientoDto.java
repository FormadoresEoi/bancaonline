package es.eoi.mundobancario.dto;

import lombok.Data;

/**
 * Movimiento DTO.
 * ===============
 *
 * DTO for the Movimiento entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class MovimientoDto {
    private int id;
    private String cuentasNumCuenta;
    private int movimientosId;
    private String descripcion;
    private String fecha;
    private double importe;
}
