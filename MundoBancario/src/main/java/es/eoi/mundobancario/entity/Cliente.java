package es.eoi.mundobancario.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "USUARIO")
	private String usuario;

	@Column(name = "PASS")
	private String pass;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "EMAIL")
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Cliente")
	private List<Cuenta> cuenta;

	public Cliente(String usuario, String pass, String nombre, String email) {
		super();
		this.usuario = usuario;
		this.pass = pass;
		this.nombre = nombre;
		this.email = email;
	}

}
