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

@Entity
@Table(name = "CUENTAS")
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUM_CUENTA", unique = true)
	private int numcuenta;
	
	@Column(name = "ALIAS")
	private String alias;
	
	@Column(name = "SALDO")
	private double saldo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCLIENTE", referencedColumnName = "ID")
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta")
	private List<Movimiento> movimientos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta")
	private List<Prestamo> prestamos;
	

}
