package com.beingatushar.ubercommons.service.base;

public interface CreateOrFindBaseService<E> {
    E createOrFind(E entity);
}
