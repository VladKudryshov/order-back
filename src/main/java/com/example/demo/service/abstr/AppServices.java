package com.example.demo.service.abstr;

import java.util.List;

public interface AppServices <T> {

    T save(T entity);

    T edit(T entity);

    void remove(Integer id);

    T get(Integer id);

    List<T> getAll();

}
