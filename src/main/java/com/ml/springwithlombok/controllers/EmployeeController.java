package com.ml.springwithlombok.controllers;

import com.ml.springwithlombok.dto.EmployeeDto;
import com.ml.springwithlombok.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/api/v1/employees"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> listEmployees() {
        log.info("EmployeeController.listEmployees() - return all employees");
        val employees = employeeService.findEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/search/{lastName}/lastName")
    public ResponseEntity<List<EmployeeDto>> listEmployeesByLastName(@PathVariable("lastName") String lastName) {
        log.info("EmployeeController.listEmployeesByLastName() - return all employees with lastName of {}", lastName);
        val employeesByLastName = employeeService.findEmployeesByLastName(lastName);
        if (employeesByLastName.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeesByLastName);
    }

    @GetMapping("/search/{employeeId}/employeeId")
    public ResponseEntity<EmployeeDto> listEmployeesByLastName(@PathVariable("employeeId") Long employeeId) {
        log.info("EmployeeController.listEmployeesByLastName(...) - return employee with id of {}", employeeId);
        val employeeById = employeeService.findEmployeeById(employeeId);
        if (employeeById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeById);
    }

    @PutMapping("/updateStatus/{employeeId}/employeeId/{employeeStatus}/employStatus")
    public ResponseEntity<EmployeeDto> updateEmployeeService(@PathVariable("employeeStatus") String employeeStatus,
                                                             @PathVariable("employeeId") Long employeeId) {
        log.info("EmployeeController.updateEmployeeService(...) - retrieving employee with employeeId of {} and updating with status {}", employeeId, employeeStatus);
        val employeeDto = employeeService.updateEmployeeStatus(employeeId, employeeStatus);
        if (null == employeeDto) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeDto);
    }

    @PutMapping("/updateEmail/{employeeId}/employeeId/{employeeEmail}/employeeEmail")
    public ResponseEntity<EmployeeDto> updateEmployeeEmail(@PathVariable("employeeEmail") String employeeEmail,
                                                           @PathVariable("employeeId") Long employeeId) {
        log.info("EmployeeController.updateEmployeeEmail(...) - retrieving employee with employeeId of {} and updating with email {}", employeeId, employeeEmail);
        val employeeDto = employeeService.updateEmployeeEmail(employeeId, employeeEmail);
        if (null == employeeDto) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeDto);
    }
}