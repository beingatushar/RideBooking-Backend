package com.beingatushar.ubercommons.service.base;

@FunctionalInterface
public interface GetByIdBaseService<E, ID> {
    E getById(ID id);
}
