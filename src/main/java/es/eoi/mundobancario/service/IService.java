package es.eoi.mundobancario.service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface.
 * ==================
 *
 * Base interface for all services.
 *
 * @param <T> Entity type.
 * @param <P> Primary Key type.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public interface IService<T, P> {
    /**
     * Finds an entity by its primary key.
     *
     * @param id Primary key.
     *
     * @return Entity with the given key.
     */
    Optional<T> find(P id);

    /**
     * Finds and returns all entities in the table.
     *
     * @return All entities.
     */
    List<T> find();

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
     */
    void delete(T entity);
}
