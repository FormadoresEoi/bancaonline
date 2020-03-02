package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.eoi.mundobancario.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

}
