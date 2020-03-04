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
	@Column(name = "num_cuenta", nullable = false)
	private int numCuenta;
	
	@Column(name = "alias")
	private String alias;
	
	@Column(name = "saldo")
	private double saldo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta")
	private List<Movimiento> movimientos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta")
	private List<Prestamo> prestamos;
	
}
