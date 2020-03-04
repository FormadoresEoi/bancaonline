package es.eoi.mundobancario.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MOVIMIENTOS")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA")
	private Date fecha;
	@Column(name = "IMPORTE")
	private double importe;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_MOVIMIENTO", referencedColumnName = "ID")
	private TipoMovimiento tipoMovimiento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_CUENTA", referencedColumnName = "NUM_CUENTA")
	private Cuenta cuenta;

	public Movimiento(String descripcion, Date fecha, double importe) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.importe = importe;
	}

}
