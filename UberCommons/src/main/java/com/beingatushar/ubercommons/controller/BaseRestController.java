package com.beingatushar.ubercommons.controller;

import com.beingatushar.ubercommons.service.BaseService;

import java.util.List;

public abstract class BaseRestController<T, ID> {

    protected abstract BaseService<T, ID> getService();

    public T create(T entity) {
        return getService().create(entity);
    }

    public T update(ID id, T entity) {
        return getService().update(id, entity);
    }

    public T getById(ID id) {
        return getService().getById(id);
    }

    public Boolean deleteById(ID id) {
        return getService().deleteById(id);
    }

    public List<T> getAll() {
        return getService().getAll();
    }
}
