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

	public Cuenta create(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public Cuenta findById(int numCuenta) {
		return cuentasRepository.findById(numCuenta).get();
	}

	public Cuenta update(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public void remove(int numCuenta) {
		cuentasRepository.deleteById(numCuenta);
	}

	public void ejecutarAmortizacionsDiarias() {
		// TODO Auto-generated method stub
		
	}

	public Cuenta createPagos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cuenta createPrestamos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cuenta createIngresos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cuenta findPrestamosAmortizados(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cuenta findPrestamosVivos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cuenta findPresatmos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cuenta findMovimientos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cuenta> findAllDeudora() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
