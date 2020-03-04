package es.eoi.mundobancario.entity;

import java.sql.Date;
import java.util.List;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prestamos")
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "fecha")	//Utilizamos Date del java util comprobar si funciona sino Date de sql util
	private Date fecha;
	@Column(name = "importe")
	private float importe;
	@Column(name="plazos")
	private int plazos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "num_cuenta", referencedColumnName = "num_cuenta")
	private Cuenta cuenta;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prestamo")
	private List<Amortizacion> listAmortizacion;

	

}
