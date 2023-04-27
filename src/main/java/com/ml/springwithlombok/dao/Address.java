package com.ml.springwithlombok.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Data
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;

    @NonNull
    private String addressType;

    @NonNull
    private String addressLine1;

    private String addressLine2;

    @NonNull
    private String city;

    @NonNull
    private String state;

    @NonNull
    private String zipCode;

    @NonNull
    private Date createdDate;
}