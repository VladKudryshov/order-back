package com.example.demo.entity.enums;

public enum Operations {
    GET(1),
    REMOVE(1),
    EDIT(2),
    SAVE(3);

    private final Integer value;

    Operations(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
