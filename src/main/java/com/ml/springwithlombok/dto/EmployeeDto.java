package com.ml.springwithlombok.dto;

import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class EmployeeDto {
    Long id;
    String lastName;
    String firstName;
    String middleName;
    String suffix;
    List<AddressDto> addresses;
    Date createdDate;
}