package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Amortizacion;

public interface AmortizacionService {

	Amortizacion FindById(int id);
	
	Amortizacion createAmortizacion(Amortizacion amortizacion);
	
	List<Amortizacion> listAmortizaciones();
	
	
}
