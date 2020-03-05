package es.eoi.mundobancario.utils;

import java.util.Calendar;

public class Fechas {

	static public Calendar queDiaEsHoy() {
		return Calendar.getInstance();
	}

	static public Calendar sumaMesesAHoy(Integer meses) {
		Calendar fecha = Calendar.getInstance();
		fecha.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH) + meses, fecha.get(Calendar.DAY_OF_MONTH));
		return fecha;
	}

}
