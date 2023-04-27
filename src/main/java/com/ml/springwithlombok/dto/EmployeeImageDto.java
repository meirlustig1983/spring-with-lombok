package com.ml.springwithlombok.dto;

import lombok.NonNull;

import java.util.Date;

public record EmployeeImageDto(
        Long id,
        @NonNull EmployeeDto employeeDto,
        @NonNull byte[] image,
        @NonNull Date createdDate) {
}