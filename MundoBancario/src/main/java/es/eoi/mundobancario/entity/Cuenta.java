package es.eoi.mundobancario.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "cuentas")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int num_cuenta;

	@Column
	String alias;

	@Column
	float saldo;

	@ManyToOne
	@JoinColumn(name = "cliente", referencedColumnName = "id")
	Cliente cliente;

	@OneToMany(mappedBy = "cuenta")
	List<Movimiento> movimientos;

	@OneToMany(mappedBy = "cuenta")
	List<Prestamo> prestamos;
	
}
