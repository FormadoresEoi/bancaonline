package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.dto.FullClienteDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

/**
 * Cliente ====================
 *
 * Repository for the 'ClienteRepository'.
 *
 * @author Carlos Sánchez <karlos.sangar@gmail.com>
 */

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
