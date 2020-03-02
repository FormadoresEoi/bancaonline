package es.eoi.mundobancario.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Amortización entity.
 * ====================
 *
 * Entity for the `amortizaciones` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
@Entity
@Table(name = "tipos_movimiento", schema = "mundobancario")
public class TipoMovimiento {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "tipo", nullable = false)
    private String tipo;
}
