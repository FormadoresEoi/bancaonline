package es.eoi.mundobancario.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.Repository.CuentasRepository;
import es.eoi.mundobancario.entity.Cuenta;

@Service
public class CuentaServiceImpl implements CuentaService {
	@Autowired
	CuentasRepository cuentasRepository;

	public Cuenta CreateCuenta(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public Optional<Cuenta> findCuentaById(int numCuenta) {
		return cuentasRepository.findById(numCuenta);
	}

	public Cuenta updateCuenta(Cuenta cuenta) {
		return cuentasRepository.save(cuenta);
	}

	public void removeCuenta(int numCuenta) {
		cuentasRepository.deleteById(numCuenta);
	}
}
