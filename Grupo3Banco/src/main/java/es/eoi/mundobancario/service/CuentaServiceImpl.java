package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cuenta;
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
}