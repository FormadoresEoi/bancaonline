package es.eoi.mundobancario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cuentas")
public class Cuenta {

	@Id
	private int num_cuenta;
	@Column(name = "alias")
	private String alias;
	@Column(name = "saldo")
	private float saldo;

}
