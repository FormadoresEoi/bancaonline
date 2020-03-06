package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.Optional;

import es.eoi.mundobancario.entity.Cliente;

/**
 * Controller interface.
 * =====================
 *
 * Base interface for all controllers.
 *
 * @param <T> DTO type.
 * @param <P> Primary key type.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IController<T, P> {
    /**
     * Find and returns all entities in the database.
     *
     * @return All entities in the database.
     */
    List<T> findAll();

    /**
     * Finds and returns an entity.
     *
     * @param id Entity id.
     *
     * @return Entity with the given id.
     */
    T findById(P id);

    /**
     * Creates an entity.
     *
     * @param entity Entity to create.
     *
     * @return Created entity.
     */
    T create(T entity);

    /**
     * Updates an entity.
     *
     * @param id     Entity to update.
     * @param entity New entity values.
     *
     * @return Updated entity.
     */
    T update(P id, T entity);
}