package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;
@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

}
