package es.eoi.mundobancario.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

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
	@Column(name = "FECHA")
	private Date fecha;
	@Column(name = "IMPORTE")
	private double importe;
	@Column(name = "PLAZOS")
	private double plazo;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prestamo")
	private List<Amortizacion> amortizacion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_CUENTA", referencedColumnName = "NUM_CUENTA")
	private Cuenta cuenta;
	public Prestamo(String descripcion, Date fecha, double importe, double plazo, List<Amortizacion> amortizacion,
			Cuenta cuenta) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.importe = importe;
		this.plazo = plazo;
		this.amortizacion = amortizacion;
		this.cuenta = cuenta;
	}
	
}
