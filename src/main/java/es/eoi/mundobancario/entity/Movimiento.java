package es.eoi.mundobancario.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Collection;
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
@Table(name = "movimientos", schema = "mundobancario")
public class Movimiento {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "cuentas_num_cuenta", nullable = false)
    private String cuentasNumCuenta;

    @Basic
    @Column(name = "movimientos_id", nullable = false)
    private int movimientosId;

    @Basic
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Basic
    @Column(name = "fecha", nullable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date fecha;

    @Basic
    @Column(name = "importe", nullable = false, precision = 2)
    private double importe;

    @ManyToOne
    @JoinColumn(name = "cuentas_num_cuenta", referencedColumnName = "num_cuenta", nullable = false, insertable = false, updatable = false)
    private Cuenta cuentasByCuentasNumCuenta;

    @ManyToOne
    @JoinColumn(name = "movimientos_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Movimiento movimientosByMovimientosId;

    @OneToMany(mappedBy = "movimientosByMovimientosId")
    private Collection<Movimiento> movimientosById;
}
