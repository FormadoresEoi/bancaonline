package es.eoi.mundobancario.excepcion;

public class MiExcepcion extends Exception {
	// Constructor
	public MiExcepcion() {
	};

	// Excepcion: Error Provocado
	public String excErrorPersonalizado() {
		return "Error provocado.";
	}
}
