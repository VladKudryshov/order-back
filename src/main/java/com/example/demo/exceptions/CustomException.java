package com.example.demo.exceptions;

import com.example.demo.entity.enums.TypeError;
import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public abstract class CustomException extends RuntimeException {

    protected String error;
    protected ObjectTypes object;
    protected Operations operation;
    protected TypeError type;

    public CustomException(String error, TypeError type, ObjectTypes object, Operations operation) {
        this.error = error;
        this.type = type;
        this.object = object;
        this.operation = operation;
    }

    public Map message() {
        return ImmutableMap.of(
                "message_error", this.error,
                "type", this.type,
                "object", this.object,
                "operation", this.operation);
    }

}
