package com.ml.springwithlombok.dto;

import java.util.Date;

public class AddressDto {
    private final Long id;
    private final String addressType;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String zipCode;
    private final Date createdDate;

    public AddressDto(Long id, String addressType, String addressLine1, String addressLine2, String city,
                      String state, String zipCode, Date createdDate) {
        this.id = id;
        this.addressType = addressType;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }


    public String getAddressType() {
        return addressType;
    }


    public String getAddressLine1() {
        return addressLine1;
    }


    public String getAddressLine2() {
        return addressLine2;
    }


    public String getCity() {
        return city;
    }


    public String getState() {
        return state;
    }


    public String getZipCode() {
        return zipCode;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

}
