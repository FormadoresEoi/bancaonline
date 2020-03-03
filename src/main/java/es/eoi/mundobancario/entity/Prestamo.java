package es.eoi.mundobancario.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

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
@Table(name = "prestamos", schema = "mundobancario")
public class Prestamo {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "cuentas_num_cuenta", nullable = false)
    private String cuentasNumCuenta;

    @Basic
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Basic
    @Column(name = "fecha", nullable = false)
    private Timestamp fecha;

    @Basic
    @Column(name = "importe", nullable = false, precision = 2)
    private double importe;

    @Basic
    @Column(name = "plazos", nullable = false)
    private int plazos;

    @OneToMany(mappedBy = "prestamosByPrestamosId")
    private Collection<Amortizacion> amortizacionesById;

    @ManyToOne
    @JoinColumn(name = "cuentas_num_cuenta", referencedColumnName = "num_cuenta", nullable = false, insertable = false, updatable = false)
    private Cuenta cuentasByCuentasNumCuenta;
}
