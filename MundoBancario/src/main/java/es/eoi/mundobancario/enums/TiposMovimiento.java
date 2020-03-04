package es.eoi.mundobancario.enums;

public enum TiposMovimiento {
	INGRESO			(0),
	PRESTAMO		(1), 
	PAGO			(2), 
	AMORTIZACION	(3), 
	INTERES			(4); 
	
	private int value;

	private TiposMovimiento(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
