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


@Entity
@Table(name = "AMORTIZACIONES")
public class Amortizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true)
	private int id;
	
	@Column(name = "FECHA")
	private Date fecha;
	
	@Column(name = "IMPORTE")
	private int importe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPRESTAMO", referencedColumnName = "ID")
	private Prestamo prestamo;

}
