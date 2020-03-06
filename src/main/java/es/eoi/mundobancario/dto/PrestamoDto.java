package es.eoi.mundobancario.dto;

import lombok.Data;

/**
 * Prestamo DTO.
 * =============
 *
 * DTO for the Prestamo entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class PrestamoDto {
    private int id;
    private String cuentasNumCuenta;
    private String descripcion;
    private String fecha;
    private double importe;
    private int plazos;
}
