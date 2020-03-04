package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer>{

}
