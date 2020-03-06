package es.eoi.mundobancario.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA")
	private Calendar fecha;
	@Column(name = "IMPORTE")
	private double importe;
	@Column(name = "PLAZOS")
	private int plazo;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prestamo", cascade = CascadeType.PERSIST)
	private List<Amortizacion> amortizacion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_CUENTA", referencedColumnName = "NUM_CUENTA")
	private Cuenta cuenta;

	public Prestamo(String descripcion, Calendar fecha, double importe, int plazo) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.importe = importe;
		this.plazo = plazo;
	}

}
