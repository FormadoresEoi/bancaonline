package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.AmortizacionDTO;
import es.eoi.mundobancario.dto.NewPrestamoDTO;
import es.eoi.mundobancario.dto.PrestamoDTO;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TiposMovimiento;
import es.eoi.mundobancario.repository.AmortizacionRepository;
import es.eoi.mundobancario.repository.CuentaRepository;
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
	public void CreatePrestamo(Prestamo prestamo) {
		
		//Insertamos el prestamo
		prestamosrepo.save(prestamo);
		//Calculamos amortizacion y la insertamos
		int a = prestamo.getImporte()/prestamo.getPlazos();
		Amortizacion amor = new Amortizacion();
		amor.setImporte(a);
		amor.setPrestamo(prestamo);
		
		amorrepo.save(amor);
		//Creamos Movimiento Prestamo
		TiposMovimiento tipo = new TiposMovimiento();
		
		tipo.setTipo("Prestamo");
		
		tiporepo.save(tipo);
		
		//Actualizamos Saldo Cuenta
		float saldoN = (float) (repository.findById(prestamo.getCuenta().getNumcuenta()).get().getSaldo() - prestamo.getImporte());
		
		repository.findById(prestamo.getCuenta().getNumcuenta()).get().setSaldo(saldoN);
		
		
		
		
		
	}
	
}