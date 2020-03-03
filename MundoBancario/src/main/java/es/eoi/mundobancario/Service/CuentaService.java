package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Cuenta;

public interface CuentaService {

	Cuenta CreateCuenta(Cuenta cuenta);

	Optional<Cuenta> findCuentaById(int id);

	Cuenta updateCuenta(Cuenta cuenta);

	void removeCuenta(int id);
}
