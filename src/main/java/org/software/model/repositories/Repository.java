package org.software.model.repositories;

import java.util.List;

public interface Repository<T, K> {
    List<T> getAll();

    T getById(K id);

    T create(T entity);

    T update(T entity);

    T delete(T entity);
}