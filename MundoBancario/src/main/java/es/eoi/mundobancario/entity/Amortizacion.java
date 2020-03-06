package es.eoi.mundobancario.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "amortizaciones")
public class Amortizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column
	@Temporal(TemporalType.DATE)
	Calendar fecha;

	@Column
	float importe;

	@Column
	String pagado;

	@ManyToOne
	@JoinColumn(name = "prestamo", referencedColumnName = "id")
	Prestamo prestamo;

}
