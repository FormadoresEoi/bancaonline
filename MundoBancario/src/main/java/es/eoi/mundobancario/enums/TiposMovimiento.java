package es.eoi.mundobancario.enums;

public enum TiposMovimiento {
	INGRESO			(1),
	PRESTAMO		(2), 
	PAGO			(3), 
	AMORTIZACION	(4), 
	INTERES			(5); 
	
	private int value;

	private TiposMovimiento(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
