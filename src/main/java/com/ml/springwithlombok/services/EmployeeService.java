package com.ml.springwithlombok.services;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dao.Employee;
import com.ml.springwithlombok.dto.AddressDto;
import com.ml.springwithlombok.dto.EmployeeDto;

import com.ml.springwithlombok.enums.EmployeeStatus;
import com.ml.springwithlombok.repositories.EmployeeImageRepository;
import com.ml.springwithlombok.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeImageRepository employeeImageRepository;

    public List<EmployeeDto> findEmployees() {
        LOGGER.info("EmployeeService.findEmployees() - retrieving all employees");
        val employees = employeeRepository.findAll();
        return employeeListConverter(employees);
    }

    public List<EmployeeDto> findEmployeesByLastName(final String lastName) {
        LOGGER.info("EmployeeService.findEmployeesByLastName() - retrieving all employees with lastName of {}", lastName);
        val employees = employeeRepository.findByLastName(lastName);
        return employeeListConverter(employees);
    }

    public EmployeeDto findEmployeeById(final Long id) {
        LOGGER.info("EmployeeService.findEmployeeById(...) - retrieving employee with id of {}", id);
        val employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            Employee emp = employee.get();
            return employeeConverter(emp);
        } else {
            return null;
        }
    }

    public EmployeeDto updateEmployeeStatus(final Long employeeId, final String employeeStatus) {
        LOGGER.info("EmployeeService.updateEmployeeStatus(...) - retrieving employee with employeeId of {} and updating with status {}", employeeId, employeeStatus);
        val employeeById = findEmployeeById(employeeId);
        val employeeDto = employeeById.withEmployeeStatus(EmployeeStatus.convert(employeeStatus));
        return updateEmployee(employeeDto);
    }

    public EmployeeDto updateEmployeeEmail(final Long employeeId, final String employeeEmail) {
        LOGGER.info("EmployeeService.updateEmployeeEmail(...) - retrieving employee with employeeId of {} and updating with email {}", employeeId, employeeEmail);
        val employeeById = findEmployeeById(employeeId);
        val employeeDto = employeeById.withEmail(employeeEmail);
        return updateEmployee(employeeDto);
    }

    public EmployeeDto updateEmployee(final EmployeeDto employeeDto) {
        val employeeImage = employeeImageRepository.findByEmployeeId(employeeDto.id());
        val employee = new Employee(
                employeeDto.id(),
                employeeDto.lastName(),
                employeeDto.firstName(),
                employeeDto.middleName(),
                employeeDto.suffix(),
                employeeDto.email(),
                null,
                employeeImage,
                employeeDto.employeeStatus(),
                employeeDto.createdDate()
        );
        employee.setAddresses(addressDtoListToAddressList(
                employeeDto.addresses(), employee)
        );

        val savedEmployee = employeeRepository.save(employee);
        return employeeConverter(savedEmployee);
    }

    private List<EmployeeDto> employeeListConverter(List<Employee> employees) {
        LOGGER.info("EmployeeService.employeeConverter - converting Employee Entity to Employee DTO");
        val employeeDtoList = new ArrayList<EmployeeDto>();
        for (Employee emp : employees) {
            employeeDtoList.add(employeeConverter(emp));
        }
        return employeeDtoList;
    }

    public static List<AddressDto> addressListConverter(List<Address> addresses) {
        LOGGER.info("EmployeeService.addressConverter - converting Address Entity to Address DTO");
        return getAddressDtoList(addresses);
    }

    static List<AddressDto> getAddressDtoList(List<Address> addresses) {
        val addressDtoList = new ArrayList<AddressDto>();
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

    static EmployeeDto employeeConverter(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getSuffix(),
                employee.getEmail(),
                addressListConverter(employee.getAddresses()),
                employee.getEmployeeStatus(),
                employee.getCreatedDate()
        );
    }

    private List<Address> addressDtoListToAddressList(List<AddressDto> addressDtos, Employee employee) {
        val addresses = new ArrayList<Address>();
        for (AddressDto addressDto : addressDtos) {
            addresses.add(
                    new Address(
                            addressDto.id(),
                            employee,
                            addressDto.addressType(),
                            addressDto.addressLine1(),
                            addressDto.addressLine2(),
                            addressDto.city(),
                            addressDto.state(),
                            addressDto.zipCode(),
                            addressDto.createdDate()
                    )
            );
        }
        return addresses;
    }
}