package com.ml.springwithlombok.dto;

import java.util.Date;

public record AddressDto(
        Long id,
        String addressType,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String zipCode,
        Date createdDate) {
}