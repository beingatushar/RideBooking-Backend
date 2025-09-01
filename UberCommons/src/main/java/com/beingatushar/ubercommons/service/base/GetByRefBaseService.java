package com.beingatushar.ubercommons.service.base;

public interface GetByRefBaseService<E, ID> {
    E getByRef(ID id);
}
