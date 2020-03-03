package es.eoi.mundobancario.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.entity.Cuenta;

@Service
public class CuentaServiceImpl implements CuentaService {
	@Autowired
	CuentasRepository cuentasRepository;

	public Cuenta Create(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public Optional<Cuenta> findById(int numCuenta) {
		return cuentasRepository.findById(numCuenta);
	}

	public Cuenta update(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public void remove(int numCuenta) {
		cuentasRepository.deleteById(numCuenta);
	}

	@Override
	public Cuenta create(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta findById(int numCuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta update(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int numCuenta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejecutarAmortizacionsDiarias() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cuenta createPagos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta createPrestamos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta createIngresos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta findPrestamosAmortizados(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta findPrestamosVivos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta findPresatmos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta findMovimientos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cuenta> findAllDeudora() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
