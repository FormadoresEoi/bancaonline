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
@Table(name = "tipos_movimiento")
public class TiposMovimiento {

	@Id
	private int id;
	@Column(name = "tipo")
	private String tipo;

}
