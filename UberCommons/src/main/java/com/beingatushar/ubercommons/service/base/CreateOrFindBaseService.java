package com.beingatushar.ubercommons.service.base;

@FunctionalInterface
public interface CreateOrFindBaseService<E> {
    E createOrFind(E entity);
}
