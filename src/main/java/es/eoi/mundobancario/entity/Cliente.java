package es.eoi.mundobancario.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

/**
 * Cliente entity.
 * ===============
 *
 * Entity for the `clientes` table.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Data
@Entity
@Table(name = "clientes", schema = "mundobancario")
public class Cliente {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Basic
    @Column(name = "pass", nullable = false)
    private String pass;

    @Basic
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "clientesByClientesId")
    private Collection<Cuenta> cuentasById;
}
