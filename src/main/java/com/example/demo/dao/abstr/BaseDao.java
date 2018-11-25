package com.example.demo.dao.abstr;

import java.util.List;
import java.util.Optional;

public interface BaseDao <T,K>{
    T save(T entity);

    T update(T entity);

    void removeById(K entityId);

    Optional<T> getById(K entityId);

    List<T> getAll() ;
}
