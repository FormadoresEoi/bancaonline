package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
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
	public Optional<Cuenta> buscarCuenta(int id) {
		return cuentrepo.findById(id);
	}

	@Override
	public Cuenta updateCuenta(Cuenta cuenta) {
		return cuentrepo.save(cuenta);
	}

	@Override
	public void removeCuenta(int id) {
		cuentrepo.deleteById(id);
	}
}
