package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Cliente;

/**
 * Cliente ====================
 *
 * Repository for the 'ClienteRepository'.
 *
 * @author Carlos Sánchez <karlos.sangar@gmail.com>
 */

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}
