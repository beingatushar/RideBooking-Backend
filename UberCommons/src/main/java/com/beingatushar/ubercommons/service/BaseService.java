package com.beingatushar.ubercommons.service;

import java.util.List;

public interface BaseService<T, ID> {

    /**
     * Create a new entity.
     */
    T create(T entity);

    /**
     * Update an existing entity.
     *
     * @throws IllegalArgumentException if entity does not exist
     */
    T update(ID id, T entity);

    /**
     * Get entity by id.
     */
    T getById(ID id);

    /**
     * Delete entity by id.
     *
     * @return true if entity was deleted, false if not found
     */
    Boolean deleteById(ID id);

    /**
     * Get all entities.
     */
    List<T> getAll();

    /**
     * check if  entity is present by id.
     *
     * @return true if entity was present, false if not found
     */
    boolean existsById(ID id);
}
