package com.ml.springwithlombok.dto;

import com.ml.springwithlombok.enums.EmployeeStatus;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

public record EmployeeDto(
        Long id,
        @NonNull String lastName,
        @NonNull String firstName,
        String middleName,
        String suffix,
        @NonNull String email,
        List<AddressDto> addresses,
        @NonNull EmployeeStatus employeeStatus,
        @NonNull Date createdDate) {
}