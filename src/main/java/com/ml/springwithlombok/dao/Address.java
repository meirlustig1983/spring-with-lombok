package com.ml.springwithlombok.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;
    private String addressType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private Date createdDate;
}