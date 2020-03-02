package es.eoi.mundobancario.Service;

import java.util.Optional;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {

	Cliente CreateCliente(Cliente cliente);

	Optional<Cliente> findClienteById(int id);

	Cliente updateCliente(Cliente cliente);

	void removeCliente(int id);
}
