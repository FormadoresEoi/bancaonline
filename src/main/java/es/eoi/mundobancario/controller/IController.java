package es.eoi.mundobancario.controller;

import java.util.List;

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
     * Updates an entity.
     *
     * @param entity Entity to update.
     *
     * @return Updated entity.
     */
    T update(T entity);

    /**
     * Deletes an entity.
     *
     * @param entity Entity to delete.
     *
     * @return Deleted entity.
     */
    T delete(T entity);
}
