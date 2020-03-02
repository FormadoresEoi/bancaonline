package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Cuenta;

public interface CuentasRepository extends JpaRepository<Cuenta, Integer> {

}
