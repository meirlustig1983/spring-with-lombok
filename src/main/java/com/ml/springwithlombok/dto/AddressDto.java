package com.ml.springwithlombok.dto;

import lombok.Value;

import java.util.Date;

@Value
public class AddressDto {
    Long id;
    String addressType;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String zipCode;
    Date createdDate;
}