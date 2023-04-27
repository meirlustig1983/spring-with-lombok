package com.ml.springwithlombok.dto;

public record RestContainer<T>(T record, String type) {
}