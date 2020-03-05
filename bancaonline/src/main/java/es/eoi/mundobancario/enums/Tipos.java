package es.eoi.mundobancario.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tipos {
		Ingreso(1, "Ingreso"),
		Prestamo(2, "Prestamo"),
		Pago(3,"Pago"),
		Amortizacion(4, "Amortizacion"),
		Interes(5, "Interes");
		
		private final int EnumCode;
		private final String EnumDesc;
		
	}