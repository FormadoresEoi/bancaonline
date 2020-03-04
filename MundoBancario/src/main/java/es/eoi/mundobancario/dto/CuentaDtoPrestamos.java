package es.eoi.mundobancario.dto;

import java.util.List;

import es.eoi.mundobancario.entity.Prestamo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDtoPrestamos {
	
	private int numeroCuenta;
	private String alias;
	private double saldo;
	private List<Prestamo> prestamo;
}
