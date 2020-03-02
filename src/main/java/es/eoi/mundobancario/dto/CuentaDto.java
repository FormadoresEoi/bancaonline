package es.eoi.mundobancario.dto;

import lombok.Data;

/**
 * Cuenta DTO.
 * ===========
 *
 * DTO for the Cuenta entity.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
public class CuentaDto {
    private String numCuenta;
    private int clientesId;
    private String alias;
    private double saldo;
}
