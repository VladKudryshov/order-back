package com.example.demo.dao.abstr;

import java.util.List;

public interface BaseDao <T,K>{
    T save(T entity);

    T update(T entity);

    boolean removeById(K entityId);

    T getEntityById(K entityId);

    List<T> getEntities();
}
