package es.eoi.mundobancario.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
@Table(name = "amortizaciones", schema = "mundobancario")
public class Amortizacion {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "prestamos_id", nullable = false)
    private int prestamosId;

    @Basic
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Basic
    @Column(name = "importe", nullable = false, precision = 2)
    private double importe;

    @ManyToOne
    @JoinColumn(name = "prestamos_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Prestamo prestamosByPrestamosId;
}
