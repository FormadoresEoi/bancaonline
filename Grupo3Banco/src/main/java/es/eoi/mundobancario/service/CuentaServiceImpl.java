package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TiposMovimiento;
import es.eoi.mundobancario.repository.AmortizacionRepository;
import es.eoi.mundobancario.repository.CuentaRepository;
import es.eoi.mundobancario.repository.MovimientoRepository;
import es.eoi.mundobancario.repository.PrestamoRepository;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CuentaServiceImpl implements CuentaService {

	public final CuentaRepository repository;
	public final PrestamoRepository prestamosrepo;
	public final AmortizacionRepository amorrepo;
	public final TipoMovimientoRepository tiporepo;
	public final MovimientoRepository movimientorepo;

	@Override
	public Cuenta findById(int id) {
		return this.repository.findById(id).get();
	}


	@Override
	public void createCuenta(Cuenta cuenta) {
		repository.save(cuenta);
	}

	@Override
	public void deleteCuenta(Cuenta cuenta) {
		repository.delete(cuenta);
	}

	@Override
	public void updateCuenta(Cuenta cuenta, String alias) {
		cuenta.setAlias(alias);
		this.repository.save(cuenta);
	}

	@Override
	public List<Cuenta> listCuentas() {
		return repository.findAll();
	}
	
	@Override
	public List<Cuenta> listDeudoras() {
		List<Cuenta> cuentas = repository.findAll();
		List<Cuenta> cuentasD = new ArrayList<Cuenta>();
		for (int i = 0; i < cuentas.size();i++)
		{
			if(cuentas.get(i).getSaldo() < 0)
			{
				cuentasD.add(cuentas.get(i));
			}
		}
		return cuentasD;
	}


	@Override
	public List<Movimiento> listMovimientos(int id) {

		return repository.findById(id).get().getMovimientos();
	}


	@Override
	public List<Prestamo> listPrestamosVivos(int id) {
		List<Prestamo> prestamos = repository.findById(id).get().getPrestamos();
		List<Prestamo> prestamosvivos = new ArrayList<Prestamo>();
		for(int i = 0; i < prestamos.size(); i++)
		{
			if (prestamos.get(i).getPlazos() > 0)
			{
				prestamosvivos.add(prestamos.get(i));
			}
		}
		return prestamosvivos;
	}


	@Override
	public List<Prestamo> listPrestamosAmortizados(int id) {
		List<Prestamo> prestamos = repository.findById(id).get().getPrestamos();
		List<Prestamo> prestamosAmortizados = new ArrayList<Prestamo>();
		for(int i = 0; i < prestamos.size(); i++)
		{
			if (prestamos.get(i).getPlazos() == 0)
			{
				prestamosAmortizados.add(prestamos.get(i));
			}
		}
		return prestamosAmortizados;
	}


	@Override
	public void CreatePrestamo(Prestamo prestamo , int id) {
		//Insertamos el prestamo
		Calendar c = Calendar.getInstance();
		Date d = new Date();

		prestamo.setCuenta(repository.findById(id).get());
		prestamosrepo.save(prestamo);
		
		int a = prestamo.getImporte()/prestamo.getPlazos(); // Sacamos el importe de las amortizaciones
		//Calculamos amortizacion y la insertamos
		List<Amortizacion> amor = new ArrayList<Amortizacion>();
		
		
		for(int i= 0; i < prestamo.getPlazos() ; i++)
		{
			Amortizacion A = new Amortizacion();
			A.setImporte(a); // Seteamos el Importe de la amortizacion.
			c.add(Calendar.MONTH, 1);
			A.setPrestamo(prestamo);
			A.setFecha(c.getTime());
			amor.add(A); // Añadimo Amortizacion a la Lista de Amortizaciones
			amorrepo.save(A);
			
		}
		
		
		
		//Creamos Movimiento Prestamo
		Movimiento tipo = new Movimiento();
		Date date = new Date();
		tipo.setDescripcion("Prestamo");
		tipo.setFecha(date);
		tipo.setCuenta(repository.findById(id).get());
		tipo.setImporte(prestamo.getImporte());
		tipo.setTiposmovimiento(tiporepo.findById(1).get());
		movimientorepo.save(tipo);
		
		//Actualizamos Saldo Cuenta
		double saldoc = repository.findById(id).get().getSaldo();
		double importec = prestamo.getImporte();
		double saldof = saldoc + importec;
		
		repository.findById(id).get().setSaldo(saldof);
		repository.save(repository.findById(id).get());
		
		
		
		
		
	}
	@Override
	public void CreateIngreso()
	{
		TiposMovimiento tipo = new TiposMovimiento();
		tipo.setTipo("Ingreso");
		tiporepo.save(tipo);
	}


	@Override
	public void CreatePago() {

		TiposMovimiento tipo = new TiposMovimiento();
		tipo.setTipo("Pago");
		tiporepo.save(tipo);
	}
	

	
	
}