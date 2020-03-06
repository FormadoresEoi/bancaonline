package es.eoi.mundobancario.service;

import es.eoi.mundobancario.entity.Prestamo;

import java.util.List;

/**
 * Prestamo service.
 * =================
 *
 * Service for the Prestamo repository.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface PrestamoService extends IService<Prestamo, Integer> {
    /**
     * Find and returns all Prestamos by its Cuenta id.
     *
     * @param id Cuenta id.
     *
     * @return Prestamos of given Cuenta.
     */
    List<Prestamo> findAllByCuentaId(String id);

    /**
     * Find and returns all Prestamos vivos by its Cuenta id.
     *
     * @param id Cuenta id.
     *
     * @return Prestamos of given Cuenta.
     */
    List<Prestamo> findAllByCuentaIdVivos(String id);

    /**
     * Find and returns all Prestamos amortizados by its Cuenta id.
     *
     * @param id Cuenta id.
     *
     * @return Prestamos of given Cuenta.
     */
    List<Prestamo> findAllByCuentaIdAmortizados(String id);

    /**
     * Funcionalidad encargada de ejecutar las amortizaciones en caso de cuya fecha coincida
     * con la del sistema, el funcionamiento se explica en detalle en la parte superior.
     */
    void ejecutarAmortizacionesDiarias();

    /**
     * Creates a prestamo and its amortizaciones.
     *
     * @param prestamo Prestamo to create.
     *
     * @return Created prestamo.
     */
    Prestamo create(Prestamo prestamo);
}