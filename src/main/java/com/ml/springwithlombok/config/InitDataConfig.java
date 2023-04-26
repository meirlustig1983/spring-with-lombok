package com.ml.springwithlombok.config;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dao.Employee;
import com.ml.springwithlombok.repositories.AddressRepository;
import com.ml.springwithlombok.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class InitDataConfig {
    @Bean
    public CommandLineRunner initData(EmployeeRepository employeeRepository,
                                         AddressRepository addressRepository) {
        return args -> {
            Employee theodoreEmployee = new Employee(null, "Roosevelt", "Theodore", "Cleveland", "Jr", null, new Date());
            Employee franklinEmployee = new Employee(null, "Franklin", "Benjamin", "Paul", "Sr", null, new Date());
            employeeRepository.save(theodoreEmployee);
            employeeRepository.save(franklinEmployee);

            Address franklinAddress = new Address(null, franklinEmployee, "home",
                    "618033 Milk Street", null, "Boston", "MA", "02109", new Date());
            Address theodoreAddress = new Address(null, theodoreEmployee, "home",
                    "28 East  20th Street", "Manahattan", "New York City", "NY", "10003", new Date());

            addressRepository.save(franklinAddress);
            addressRepository.save(theodoreAddress);

        };
    }
}