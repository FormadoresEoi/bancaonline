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
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Cuentas")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUMCUENTA")
	private int NumeroCuenta;

	@Column(name = "ALIAS")
	private String Alias;

	@Column(name = "SALDO")
	private String Saldo;

	@Column(name = "ID_CLIENTE")
	private String IdCliente;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Cuenta")
	private List<Movimiento> movimiento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Cuenta")
	private List<Prestamo> prestamo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
	private Cliente cliente;

	public Cuenta(String alias, String saldo, String idCliente, List<Movimiento> movimiento, List<Prestamo> prestamo,
			Cliente cliente) {
		super();
		Alias = alias;
		Saldo = saldo;
		IdCliente = idCliente;
		this.movimiento = movimiento;
		this.prestamo = prestamo;
		this.cliente = cliente;
	}

}
