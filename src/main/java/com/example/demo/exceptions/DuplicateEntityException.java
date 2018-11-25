package com.example.demo.exceptions;

import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.TypeError;

public class DuplicateEntityException extends CustomException {
    public DuplicateEntityException(String error, TypeError type, ObjectTypes object, Operations operation) {
        super(error, type, object, operation);
    }
}
