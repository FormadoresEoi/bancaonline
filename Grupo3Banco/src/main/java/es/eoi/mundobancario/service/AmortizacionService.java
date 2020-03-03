package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Amortizacion;

public interface AmortizacionService {

	//Buscar Amortizaciones por ID
	
	public Amortizacion FindById(int id);
	
	//Crear una amortizacion
	
	public Amortizacion createAmortizacion(Amortizacion amortizacion);
	
	//Lista de amortizaciones
	
	public List<Amortizacion> listAmortizaciones();
}
