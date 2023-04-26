package com.ml.springwithlombok.controllers;

import com.ml.springwithlombok.dto.RestContainer;
import com.ml.springwithlombok.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/rest/employee/all")
    public RestContainer<?> listEmployees() {
        return employeeService.findEmployees();
    }

    @GetMapping("/rest/employee/search/{lastName}")
    public RestContainer<?> listEmployeesByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.findEmployeesByLastName(lastName);
    }

    @GetMapping("/rest/employee/get/{employeeId}")
    public RestContainer<?> listEmployeesByLastName(@PathVariable("employeeId") Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

}
