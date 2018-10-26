package com.example.demo.exceptions;

import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public abstract class CustomException extends RuntimeException {

    protected String error;
    protected ObjectTypes object;
    protected Operations operation;

    public CustomException(String error, ObjectTypes object, Operations operation) {
        this.error = error;
        this.object = object;
        this.operation = operation;
    }

    public Map message(){
        return ImmutableMap.of(
                "message_error", error,
                "type_error", this.getClass().getSimpleName(),
                "object", object,
                "operation", operation);
    }

}
