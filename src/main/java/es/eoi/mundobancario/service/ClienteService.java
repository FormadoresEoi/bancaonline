package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

/**
 * Cliente service
 * ===============
 * 
 * Service for the Cliente repository.
 * 
 * @author Carlos Sanchez <karlos.sangar@gmail.com>
 */
public interface ClienteService extends IService<Cliente, Integer> {

	List<Cuenta> findCuentasCliente(Cuenta cuenta);
	

}
