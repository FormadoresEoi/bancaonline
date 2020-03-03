package es.eoi.mundobancario.Service;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {

	Cliente Create(Cliente cliente);

	Cliente findById(int id);

	List<Cliente> findAll();

	Cliente update(Cliente cliente);

	void remove(int id);

	Cliente login(int id);

	Cliente findCuentas(int id);

	Cliente updateEmail(int id);

	Cliente findPrestamos(int id);
}
