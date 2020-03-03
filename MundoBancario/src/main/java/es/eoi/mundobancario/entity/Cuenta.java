package es.eoi.mundobancario.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.eoi.mundobancario.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cuentas")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUMCUENTA")
	private int numeroCuenta;

	@Column(name = "ALIAS")
	private String alias;

	@Column(name = "SALDO")
	private double saldo;

	@Column(name = "ID_CLIENTE")
	private int idCliente;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Cuenta")
	private List<Movimiento> movimiento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Cuenta")
	private List<Prestamo> prestamo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
	private Cliente cliente;

	public Cuenta(String alias, double saldo, int idCliente) {
		super();
		this.alias = alias;
		this.saldo = saldo;
		this.idCliente = idCliente;
	}

}
