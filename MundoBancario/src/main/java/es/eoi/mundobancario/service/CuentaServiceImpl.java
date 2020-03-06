package es.eoi.mundobancario.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TiposMovimiento;
import es.eoi.mundobancario.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	CuentaRepository cuentrepo;

	@Override
	public List<Cuenta> MostrarCuenta() {
		return cuentrepo.findAll();
	}

	@Override
	public Cuenta InsertarCuenta(Cuenta cuenta) {
		return cuentrepo.save(cuenta);
	}
	
	

	@Override
	public Optional<Cuenta> buscarCuenta(int num_cuenta) {
		return cuentrepo.findById(num_cuenta);
	}

	@Override
	public Cuenta updateCuenta(Cuenta cuenta) {
		return cuentrepo.save(cuenta);
	}

	@Override
	public void removeCuenta(int id) {
		cuentrepo.deleteById(id);
	}

	@Override
	public List<Cuenta> findAllById_Clientes(Cliente cliente) {
		
		return cuentrepo.findAllByCliente(cliente);
	}

	@Override
	public List<Cuenta> buscarCuentasDeudoras(float zero) {
		return cuentrepo.findAllBySaldoLessThan(zero);
	}
	
	@Override
	public void ActualizarSaldoPrestamo (Prestamo prestamo, Cuenta cuenta) {
		Cuenta cuentafinal= cuenta;
		cuentafinal.setSaldo(cuenta.getSaldo()+prestamo.getImporte());
		this.updateCuenta(cuentafinal);	
	}
	@Override
	public void ActualizarSaldoAmortizacion(Amortizacion amortizacion,Cuenta cuenta) {
		Cuenta cuentaFinal = cuenta;
		cuentaFinal.setSaldo(cuenta.getSaldo()-amortizacion.getImporte());
	}
	
	//@Override
	
//	public Movimiento ejecutarAmortizacionesDiarias (Amortizacion  amortizacion, Cuenta  cuenta, TiposMovimiento tiposmovimiento)
//	{
//	
//		
//	}
}
