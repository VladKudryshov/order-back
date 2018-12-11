package com.example.demo.controllers.abstr;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.OperationActionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public abstract class IControllerApp<T,K> {
    protected abstract T get(K id);

    protected abstract List<T> getAll();

    protected abstract void remove(K id);

    protected abstract T save(T entity);

    protected abstract T edit(T entity);

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public  @ResponseBody
    Map handlException(DataNotFoundException error) {
        return error.message();
    }


    @ExceptionHandler(OperationActionException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public  @ResponseBody
    Map handleParamException(OperationActionException error) {
        return error.message();
    }
}
