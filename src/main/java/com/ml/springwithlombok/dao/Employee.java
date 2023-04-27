package com.ml.springwithlombok.dao;

import com.ml.springwithlombok.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String suffix;
    private String email;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private EmployeeImage employeeImage;
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private EmployeeStatus employeeStatus = EmployeeStatus.FULL_TIME;
    private Date createdDate;
}