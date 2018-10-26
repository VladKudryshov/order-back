package com.example.demo.exceptions;

import com.example.demo.entity.enums.ObjectTypes;
import com.example.demo.entity.enums.Operations;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class InvalidParamsException extends CustomException {
    private List<String> params;
    private static final String ERROR = "Invalid params from request.";
    public InvalidParamsException(List<String> params, ObjectTypes type, Operations operation) {
        super(ERROR, type, operation);
        this.params = params;
    }

    @Override
    public Map message() {
        return ImmutableMap.of(
                "message_error", error,
                "params_error", StringUtils.join(params,','),
                "type_error", this.getClass().getSimpleName(),
                "object", object,
                "operation", operation);
    }
}
