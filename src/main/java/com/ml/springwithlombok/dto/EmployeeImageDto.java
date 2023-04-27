package com.ml.springwithlombok.dto;

import java.util.Date;

public record EmployeeImageDto(
        Long id,
        EmployeeDto employeeDto,
        byte[] image,
        Date createdDate) {
}