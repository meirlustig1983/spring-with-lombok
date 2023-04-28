package com.ml.springwithlombok.config;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dao.Employee;
import com.ml.springwithlombok.dao.EmployeeImage;
import com.ml.springwithlombok.repositories.AddressRepository;
import com.ml.springwithlombok.repositories.EmployeeImageRepository;
import com.ml.springwithlombok.repositories.EmployeeRepository;
import com.ml.springwithlombok.services.FileService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class InitDataConfig {

    @Bean
    public CommandLineRunner initData(EmployeeRepository employeeRepository,
                                      AddressRepository addressRepository,
                                      EmployeeImageRepository employeeImageRepository,
                                      FileService fileService) {
        return args -> {

            val employeeBuilder = Employee.builder();
            val theodoreEmployee = employeeBuilder
                    .firstName("Theodore")
                    .lastName("Roosevelt")
                    .middleName("Cleveland")
                    .suffix("Jr")
                    .email("tcr@gmail.com")
                    .build();

            val franklinEmployee = employeeBuilder
                    .firstName("Franklin")
                    .lastName("Benjamin")
                    .middleName("Paul")
                    .suffix("Sr")
                    .email("fbp@gmail.com")
                    .build();

            employeeRepository.save(theodoreEmployee);
            employeeRepository.save(franklinEmployee);

            val addressBuilder = Address.builder();

            val franklinAddress = addressBuilder
                    .employee(franklinEmployee)
                    .addressType("home")
                    .addressLine1("618033 Milk Street")
                    .city("Boston")
                    .state("MA")
                    .zipCode("02109")
                    .build();

            val theodoreAddress = addressBuilder
                    .employee(theodoreEmployee)
                    .addressType("home")
                    .addressLine1("28 East 20th Street")
                    .addressLine2("Manhattan")
                    .city("New York City")
                    .state("NY")
                    .zipCode("10003")
                    .build();

            addressRepository.save(franklinAddress);
            addressRepository.save(theodoreAddress);

            val employeeImageBuilder = EmployeeImage.builder();

            val franklinImg = fileService.retrieveStockImage("benjamin_franklin.png");
            val rooseveltImg = fileService.retrieveStockImage("theo_roosevelt.png");

            val theodoreImage = employeeImageBuilder
                    .employee(theodoreEmployee)
                    .image(rooseveltImg)
                    .build();

            val franklinImage = employeeImageBuilder
                    .employee(franklinEmployee)
                    .image(franklinImg)
                    .build();

            employeeImageRepository.save(theodoreImage);
            employeeImageRepository.save(franklinImage);
        };
    }
}