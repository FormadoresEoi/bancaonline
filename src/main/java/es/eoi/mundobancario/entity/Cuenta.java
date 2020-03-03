package es.eoi.mundobancario.entity;

import lombok.Data;

import javax.persistence.*;
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
@Table(name = "cuentas", schema = "mundobancario")
public class Cuenta {
    @Id
    @Column(name = "num_cuenta", nullable = false)
    private String numCuenta;

    @Basic
    @Column(name = "clientes_id", nullable = false)
    private int clientesId;

    @Basic
    @Column(name = "alias", nullable = false)
    private String alias;

    @Basic
    @Column(name = "saldo", nullable = false, precision = 2)
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "clientes_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Cliente clientesByClientesId;

    @OneToMany(mappedBy = "cuentasByCuentasNumCuenta")
    private Collection<Movimiento> movimientosByNumCuenta;

    @OneToMany(mappedBy = "cuentasByCuentasNumCuenta")
    private Collection<Prestamo> prestamosByNumCuenta;
}
