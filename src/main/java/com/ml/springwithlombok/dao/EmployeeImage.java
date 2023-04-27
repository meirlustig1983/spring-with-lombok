package com.ml.springwithlombok.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Entity
public class EmployeeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NonNull
    @ToString.Exclude
    @OneToOne(mappedBy = "employeeImage")
    private Employee employee;

    @NonNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Lob
    private byte[] image;

    @NonNull
    @Builder.Default
    private Date createdDate = new Date();
}
