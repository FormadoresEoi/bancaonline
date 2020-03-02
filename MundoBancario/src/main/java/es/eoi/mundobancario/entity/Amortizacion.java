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

import es.eoi.mundobancario.entity.Prestamo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Amortizaciones")
public class Amortizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "ID_PRESTAMO")
	private int IdPrestamo;

	@Column(name = "FECHA")
	private Date Fecha;

	@Column(name = "IMPORTE")
	private double Importe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", referencedColumnName = "ID")
	private Prestamo prestamo;

	public Amortizacion(int idPrestamo, Date fecha, double importe, Prestamo prestamo) {
		super();
		IdPrestamo = idPrestamo;
		Fecha = fecha;
		Importe = importe;
		this.prestamo = prestamo;
	}

}