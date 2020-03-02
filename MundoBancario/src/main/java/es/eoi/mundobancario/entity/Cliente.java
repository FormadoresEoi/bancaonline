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
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "USUARIO")
	private String Usuario;

	@Column(name = "PASS")
	private String Pass;

	@Column(name = "NOMBRE")
	private String Nombre;

	@Column(name = "EMAIL")
	private String Email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Cliente")
	private List<Cuenta> cuenta;

	public Cliente(String usuario, String pass, String nombre, String email, List<Cuenta> cuenta) {
		super();
		Usuario = usuario;
		Pass = pass;
		Nombre = nombre;
		Email = email;
		this.cuenta = cuenta;
	}

}
