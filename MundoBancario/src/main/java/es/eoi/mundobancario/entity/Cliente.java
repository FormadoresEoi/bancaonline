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
@Table(name = "clientes")
public class Cliente {
	@Id
	private int id;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "pass")
	private String pass;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "email")
	private String email;
}
