package com.beingatushar.ubercommons.service.base;

import jakarta.persistence.EntityNotFoundException;

/**
 * A functional interface for services that need to fetch a lazy-loaded entity reference (a proxy).
 * <p>
 * This is a performance optimization used to avoid a database round trip when you only need to
 * associate an entity without loading its full state. It's particularly useful for setting
 * foreign key relationships when creating or updating another entity.
 * <p>
 * For example, when creating a {@code Booking}, you might have the {@code userId} and {@code driverId}.
 * Instead of fetching the full {@code User} and {@code Driver} entities from the database,
 * you can use {@code getByRef} to get their proxies and set the relationship, saving unnecessary database queries.
 * The actual database hit is deferred until a field other than the ID is accessed on the proxy.
 *
 * @param <E>  The type of the entity.
 * @param <ID> The type of the entity's primary key.
 */
@FunctionalInterface
public interface GetByRefBaseService<E, ID> {

    /**
     * Gets a lazy-loaded reference to an entity by its ID.
     * <p>
     * This method does not immediately hit the database. It returns a proxy object with only the given ID initialized.
     * A database query will only be triggered if a method other than the ID getter is called
     * on the returned proxy.
     *
     * @param id The primary key of the entity. Must not be {@literal null}.
     * @return An entity proxy. Never {@literal null}.
     * @throws EntityNotFoundException if the entity with the given ID is not found when the proxy is eventually accessed.
     */
    E getByRef(ID id);
}