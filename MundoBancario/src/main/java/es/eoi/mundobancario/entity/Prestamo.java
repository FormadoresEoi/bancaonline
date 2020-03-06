package es.eoi.mundobancario.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "prestamos")
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column
	String descripcion;

	@Column
	@Temporal(TemporalType.DATE)
	Calendar fecha;

	@Column
	float importe;

	@Column
	int plazos;

	@Column(columnDefinition = "varchar(20) default 'PENDIENTE'")
	String pagado;

	@ManyToOne
	@JoinColumn(name = "cuenta", referencedColumnName = "num_cuenta")
	Cuenta cuenta;

	@OneToMany(mappedBy = "prestamo")
	List<Amortizacion> amortizaciones;

	public Prestamo() {
		amortizaciones = new ArrayList<Amortizacion>();
	}

	public void addAmortizacion(Amortizacion amortizacion) {
		amortizaciones.add(amortizacion);
	}
}
