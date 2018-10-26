package com.example.demo.controllers.abstr;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.InvalidParamsException;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public abstract class IControllerApp<T> {
    protected abstract T getEntity(@RequestBody T entity);

    protected abstract List<T> getEntities();

    protected abstract Boolean removeEntity(@RequestBody T entity);

    protected abstract T saveEntity(@RequestBody T entity);

    protected abstract T editEntity(@RequestBody T entity);

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public  @ResponseBody
    Map handlException(DataNotFoundException error) {
        return error.message();
    }

    @ExceptionHandler(InvalidParamsException.class)
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    public  @ResponseBody
    Map handleParamException(InvalidParamsException error) {
        return error.message();
    }
}
