package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

}
