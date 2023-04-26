package com.ml.springwithlombok.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", employee=" + employee +
                ", addressType='" + addressType + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(employee, address.employee) && Objects.equals(addressType, address.addressType) && Objects.equals(addressLine1, address.addressLine1) && Objects.equals(addressLine2, address.addressLine2) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(zipCode, address.zipCode) && Objects.equals(createdDate, address.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, addressType, addressLine1, addressLine2, city, state, zipCode, createdDate);
    }
}
