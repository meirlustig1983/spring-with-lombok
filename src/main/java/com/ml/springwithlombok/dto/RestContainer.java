package com.ml.springwithlombok.dto;

public class RestContainer<T> {
    final private T record;
    final private String type;

    public RestContainer(T record, String type) {
        this.record = record;
        this.type = type;
    }

    public T getRecord() {
        return record;
    }

    public String getType() {
        return type;
    }
}
