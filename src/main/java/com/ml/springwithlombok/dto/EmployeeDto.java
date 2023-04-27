package com.ml.springwithlombok.dto;

import java.util.Date;
import java.util.List;

public record EmployeeDto(
        Long id,
        String lastName,
        String firstName,
        String middleName,
        String suffix,
        List<AddressDto> addresses,
        Date createdDate) {
}