package es.eoi.mundobancario.excepcion;

@SuppressWarnings("serial")
public class NotMoneyEnoughtException extends Exception {
	public NotMoneyEnoughtException() {
		super("No queda dinero suficiente en la cuenta");
	}
}
