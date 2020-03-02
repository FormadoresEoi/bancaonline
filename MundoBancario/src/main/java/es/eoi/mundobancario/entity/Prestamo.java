package es.eoi.mundobancario.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prestamos")
public class Prestamo {
	
	@Id
	private int id;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "fecha")	//Utilizamos Date del java util comprobar si funciona sino Date de sql util
	private Date fecha;
	@Column(name = "importe")
	private float importe;
	

}
