package es.eoi.mundobancario.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Amortizaciones")
public class Amortizacion {

	@Id
	private int id;
	@Column(name = "fecha")	//Utilizamos Date del java util comprobar si funciona sino Date de sql util
	private Date fecha;
	@Column(name = "importe")
	private float importe;
}
