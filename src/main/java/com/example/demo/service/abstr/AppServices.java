package com.example.demo.service.abstr;

import java.util.List;

public interface AppServices <T> {

    T saveEntity(T entity);

    T editEntity(T entity);

    boolean removeEntity(T entity);

    T getEntity(T entity);

    List<T> getAll();

}
