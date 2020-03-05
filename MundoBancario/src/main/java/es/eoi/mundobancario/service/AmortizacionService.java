package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Prestamo;

public interface AmortizacionService {

	List<Amortizacion> MostrarAmortizacion();
	
	Amortizacion CrearAmortizacion(Amortizacion amortizacion);

	void CrearAmortizaciones(List <Amortizacion> amortizaciones);

	Optional<Amortizacion> buscarAmortizacion(int id);

	Amortizacion updateAmortizacion(Amortizacion amortizacion);

	void removeAmortizacion(int id);
   
	List <Amortizacion> calcularAmortizacion(Prestamo prestamo) ;

}