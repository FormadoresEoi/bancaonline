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
@Table(name = "MOVIMIENTOS")
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true)
	private int id;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "FECHA")
	private Date fecha;
	
	@Column(name = "IMPORTE")
	private double importe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUMEROCUENTA", referencedColumnName = "NUM_CUENTA")
	private Cuenta cuenta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMOVIMIENTO", referencedColumnName = "ID")
	private TipoMovimiento tipomovimiento;

}
