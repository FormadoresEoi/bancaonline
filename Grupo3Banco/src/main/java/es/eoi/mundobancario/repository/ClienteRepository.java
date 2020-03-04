package es.eoi.mundobancario.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

}