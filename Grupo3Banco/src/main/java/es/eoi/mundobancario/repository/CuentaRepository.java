package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}
