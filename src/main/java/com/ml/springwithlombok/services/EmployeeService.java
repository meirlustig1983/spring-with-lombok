package com.ml.springwithlombok.services;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dao.Employee;
import com.ml.springwithlombok.dto.AddressDto;
import com.ml.springwithlombok.dto.EmployeeDto;
import com.ml.springwithlombok.dto.RestContainer;

import com.ml.springwithlombok.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public RestContainer<?> findEmployees() {
        LOGGER.info("EmployeeService.findEmployees() - retrieving all employees");
        final List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return new RestContainer<>("No Records Found", "Employee");
        } else {
            return new RestContainer<>(employeeListConverter(employees), "Employee");
        }
    }

    public RestContainer<?> findEmployeesByLastName(String lastName) {
        LOGGER.info("EmployeeService.findEmployeesByLastName - retrieving all employees with lastName of {}", lastName);
        final List<Employee> employees = employeeRepository.findByLastName(lastName);
        if (employees.isEmpty()) {
            return new RestContainer<>("No Employees Found", "Employee");
        } else {
            return new RestContainer<>(employeeListConverter(employees), "Employee");
        }
    }

    public RestContainer<?> findEmployeeById(Long id) {
        LOGGER.info("EmployeeService.findEmployeeById(...) - retrieving employee with id of {}", id);
        final Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            Employee emp = employee.get();
            return new RestContainer<>(
                    employeeConverter(emp), "employee"
            );
        } else {
            return new RestContainer<>("No Record Found", "Employee");
        }
    }

    private List<EmployeeDto> employeeListConverter(List<Employee> employees) {
        LOGGER.info("EmployeeService.employeeConverter - converting Employee Entity to Employee DTO");
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for (Employee emp : employees) {
            employeeDtoList.add(
                    employeeConverter(emp)
            );
        }
        return employeeDtoList;
    }

    private List<AddressDto> addressListConverter(List<Address> addresses) {
        LOGGER.info("EmployeeService.addressConverter - converting Address Entity to Address DTO");
        return getAddressDtoList(addresses);
    }

    static List<AddressDto> getAddressDtoList(List<Address> addresses) {
        List<AddressDto> addressDtoList = new ArrayList<>();
        for (Address address : addresses) {
            addressDtoList.add(
                    new AddressDto(
                            address.getId(),
                            address.getAddressType(),
                            address.getAddressLine1(),
                            address.getAddressLine2(),
                            address.getCity(),
                            address.getState(),
                            address.getZipCode(),
                            address.getCreatedDate()
                    )
            );
        }
        return addressDtoList;
    }

    private EmployeeDto employeeConverter(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getSuffix(),
                addressListConverter(employee.getAddresses()),
                employee.getCreatedDate()
        );
    }
}
