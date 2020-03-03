package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.entity.Cuenta;

@Service
public class CuentaServiceImpl implements CuentaService {

	CuentasRepository cuentasrepository;

	public Cuenta CreateCuenta(Cuenta cuenta) {
		return cuentasrepository.save(cuenta);
	}

	public Optional<Cuenta> findCuentaById(int id) {
		return cuentasrepository.findById(id);
	}

	public Cuenta updateCuenta(Cuenta cuenta) {
		return cuentasrepository.save(cuenta);
	}

	public void removeCuenta(int id) {
		cuentasrepository.deleteById(id);
	}
}
