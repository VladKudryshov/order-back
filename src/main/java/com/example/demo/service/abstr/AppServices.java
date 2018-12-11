package com.example.demo.service.abstr;

import java.util.List;

public interface AppServices <T,K> {

    T save(T entity);

    T edit(T entity);

    void remove(K field);

    T get(K field);

    List<T> getAll();

}
