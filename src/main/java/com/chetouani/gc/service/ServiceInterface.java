package com.chetouani.gc.service;

import com.chetouani.gc.exception.EntityNotFoundException;
import org.springframework.data.repository.CrudRepository;

public interface ServiceInterface<T> {

    String ENTITY_NOT_FOUND_BY_ID_MESSAGE = "%s entity with id %d doesn't exist";

    T add(T entity);

    T update(Long id, T entity);

    default void checkIfEntityExist(CrudRepository repository, String entityName, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID_MESSAGE, entityName, id));
        }
    }
}
