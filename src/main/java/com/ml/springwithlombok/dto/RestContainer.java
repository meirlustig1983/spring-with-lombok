package com.ml.springwithlombok.dto;

public class RestContainer<T> {
    private final T record;
    private final String type;

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
