package com.ml.springwithlombok.repositories;

import com.ml.springwithlombok.dao.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);

    List<Employee> findAll();
}
