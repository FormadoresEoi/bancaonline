package es.eoi.mundobancario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Prestamo;
@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

}
