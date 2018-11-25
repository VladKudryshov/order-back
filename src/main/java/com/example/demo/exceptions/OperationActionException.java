package com.example.demo.exceptions;

import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.example.demo.entity.enums.TypeError;

public class OperationActionException extends CustomException {
    public OperationActionException(final String error,
                                    final TypeError type,
                                    final ObjectTypes object,
                                    final Operations operation) {
        super(error, type, object, operation);
    }
}
