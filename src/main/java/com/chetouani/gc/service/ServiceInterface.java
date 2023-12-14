package com.chetouani.gc.service;

public interface ServiceInterface<T> {

    T add(T entity);

    T update(Long id, T entity);
}
