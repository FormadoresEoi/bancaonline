package es.eoi.mundobancario.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
	private List<Prestamo> prestamo;
}
