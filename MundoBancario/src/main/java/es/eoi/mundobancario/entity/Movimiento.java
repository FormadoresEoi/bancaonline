package es.eoi.mundobancario.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movimientos")
public class Movimiento {
	
	@Id
	private int id;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "fecha")	//Utilizamos Date del java util comprobar si funciona sino Date de sql util
	private Date fecha;
	@Column(name = "importe")
	private float importe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private TiposMovimiento tiposmovimiento;
}
