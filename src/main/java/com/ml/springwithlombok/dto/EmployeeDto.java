package com.ml.springwithlombok.dto;

import java.util.Date;
import java.util.List;

public class EmployeeDto {
    private final Long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String suffix;
    private final List<AddressDto> addresses;
    private final Date createdDate;

    public EmployeeDto(Long id, String lastName, String firstName, String middleName,
                       String suffix, List<AddressDto> addresses, Date createdDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.addresses = addresses;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }


    public String getLastName() {
        return lastName;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getMiddleName() {
        return middleName;
    }


    public String getSuffix() {
        return suffix;
    }


    public List<AddressDto> getAddresses() {
        return addresses;
    }


    public Date getCreatedDate() {
        return createdDate;
    }
}
