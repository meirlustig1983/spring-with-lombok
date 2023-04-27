package com.ml.springwithlombok.dto;

import com.ml.springwithlombok.enums.EmployeeStatus;
import lombok.NonNull;
import lombok.With;

import java.util.Date;
import java.util.List;

public record EmployeeDto(
        Long id,
        @NonNull String lastName,
        @NonNull String firstName,
        String middleName,
        String suffix,
        @With @NonNull String email,
        List<AddressDto> addresses,
        @With @NonNull EmployeeStatus employeeStatus,
        @NonNull Date createdDate) {
}