package com.ml.springwithlombok.repositories;

import com.ml.springwithlombok.dao.EmployeeImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeImageRepository extends CrudRepository<EmployeeImage, Long> {
    EmployeeImage findByEmployeeId(final Long employeeId);

    List<EmployeeImage> findAll();
}
