package com.ml.springwithlombok.dao;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
public class Employee {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String suffix;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;
    private Date createdDate;

    public Employee() {
    }

    public Employee(String lastName, String firstName, String middleName, String suffix, Date createdDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.createdDate = createdDate;
    }

    public Employee(String lastName, String firstName, String middleName, String suffix,
                    List<Address> addresses, Date createdDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.addresses = addresses;
        this.createdDate = createdDate;
    }
}
