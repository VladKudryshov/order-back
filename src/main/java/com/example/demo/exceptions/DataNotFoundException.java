package com.example.demo.exceptions;

import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;

public class DataNotFoundException extends CustomException {
    public DataNotFoundException(String error, ObjectTypes type, Operations operation) {
        super(error, type, operation);
    }
}
