package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoService {

	public Prestamo getById(Integer id);

	public List<Prestamo> getAll();

	public List<Prestamo> getByCuenta(Cuenta cuenta);

	public List<Prestamo> getPrestamosVivosByCuentaId(Integer idcuenta);
	
	public List<Prestamo> getPrestamosVivosAll();

	public List<Prestamo> getPrestamosAmortizados(Integer idcuenta);
	
	public List<Prestamo> getPrestamosAmortizados();
	
	public Optional<Prestamo> getByCuentaAndPagado(Cuenta cuenta, String pagado);
	
	List<Prestamo> getPrestamosRecienPagados();

	public boolean post(Prestamo prestamo);

}
