package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Cuenta;

public interface CuentaService {

	Cuenta Create(Cuenta cuenta);

	Optional<Cuenta> findById(int numCuenta);

	Cuenta update(Cuenta cuenta);

	void remove(int numCuenta);
}
