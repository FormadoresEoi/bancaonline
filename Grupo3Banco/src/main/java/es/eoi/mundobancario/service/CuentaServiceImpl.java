package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CuentaServiceImpl implements CuentaService {

	public final CuentaRepository repository;

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
	
}