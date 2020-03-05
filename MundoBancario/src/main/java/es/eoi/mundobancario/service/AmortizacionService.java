package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Amortizacion;

public interface AmortizacionService {

	public Amortizacion getById(Integer id);

	public List<Amortizacion> getAll();
	
	public boolean post(Amortizacion amortizacion);
	
	public boolean ejecutarAmortizacionesDiarias();

}
