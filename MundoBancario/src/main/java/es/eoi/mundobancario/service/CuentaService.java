package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

public interface CuentaService {

	public Cuenta getById(Integer id);

	public List<Cuenta> getAll();

	public List<Cuenta> getDeudoras();

	public List<Cuenta> getByCliente(Cliente cliente);

	public boolean post(Cuenta cuenta);

	public boolean putAlias(Integer id, String alias);

}
