package es.eoi.mundobancario.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "movimientos")
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column
	String descripcion;

	@Column
	@CreatedDate
	Date fecha;

	@Column
	float importe;

	@ManyToOne
	@JoinColumn(name = "tipo", referencedColumnName = "id")
	TipoMovimiento tipo;

	@ManyToOne
	@JoinColumn(name = "cuenta", referencedColumnName = "num_cuenta")
	Cuenta cuenta;

}
