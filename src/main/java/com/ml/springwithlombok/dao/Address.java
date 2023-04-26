package com.ml.springwithlombok.dao;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
public class Address {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    private String addressType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private Date createdDate;

    public Address() {
    }

    public Address(Employee employee, String addressType, String addressLine1, String addressLine2,
                   String city, String state, String zipCode, Date createdDate) {
        this.employee = employee;
        this.addressType = addressType;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.createdDate = createdDate;
    }
}
