package com.ml.springwithlombok.config;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dao.Employee;
import com.ml.springwithlombok.dao.EmployeeImage;
import com.ml.springwithlombok.repositories.AddressRepository;
import com.ml.springwithlombok.repositories.EmployeeImageRepository;
import com.ml.springwithlombok.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Configuration
public class InitDataConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger(InitDataConfig.class);

    @Bean
    public CommandLineRunner initData(EmployeeRepository employeeRepository,
                                      AddressRepository addressRepository,
                                      EmployeeImageRepository employeeImageRepository) {
        return args -> {

            final Employee.EmployeeBuilder employeeBuilder = Employee.builder();
            Employee theodoreEmployee = employeeBuilder
                    .firstName("Theodore")
                    .lastName("Roosevelt")
                    .middleName("Cleveland")
                    .suffix("Jr")
                    .email("tcr@gmail.com")
                    .createdDate(new Date())
                    .build();

            Employee franklinEmployee = employeeBuilder
                    .firstName("Franklin")
                    .lastName("Benjamin")
                    .middleName("Paul")
                    .suffix("Sr")
                    .email("fbp@gmail.com")
                    .createdDate(new Date())
                    .build();

            employeeRepository.save(theodoreEmployee);
            employeeRepository.save(franklinEmployee);

            final Address.AddressBuilder addressBuilder = Address.builder();

            Address franklinAddress = addressBuilder
                    .employee(franklinEmployee)
                    .addressType("home")
                    .addressLine1("618033 Milk Street")
                    .city("Boston")
                    .state("MA")
                    .zipCode("02109")
                    .createdDate(new Date())
                    .build();

            Address theodoreAddress = addressBuilder
                    .employee(theodoreEmployee)
                    .addressType("home")
                    .addressLine1("28 East 20th Street")
                    .addressLine2("Manhattan")
                    .city("New York City")
                    .state("NY")
                    .zipCode("10003")
                    .createdDate(new Date())
                    .build();

            addressRepository.save(franklinAddress);
            addressRepository.save(theodoreAddress);

            final EmployeeImage.EmployeeImageBuilder employeeImageBuilder = EmployeeImage.builder();

            byte[] franklinImg = retrieveStockImage("benjamin_franklin.png");
            byte[] rooseveltImg = retrieveStockImage("theo_roosevelt.png");

            EmployeeImage theodoreImage = employeeImageBuilder
                    .employee(theodoreEmployee)
                    .image(rooseveltImg)
                    .createdDate(new Date())
                    .build();

            EmployeeImage franklinImage = employeeImageBuilder
                    .employee(franklinEmployee)
                    .image(franklinImg)
                    .createdDate(new Date())
                    .build();

            employeeImageRepository.save(theodoreImage);
            employeeImageRepository.save(franklinImage);
        };
    }

    private byte[] retrieveStockImage(String imageName) throws IOException {
        LOGGER.info("LombokDemoApplication.retrieveStockImage() - attempting to retrieve the image");
        InputStream in = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource(imageName);
            in = classPathResource.getInputStream();
            byte[] imageBytes = StreamUtils.copyToByteArray(in);
            LOGGER.info("LombokDemoApplication.retrieveStockImage() - byte[] length = " + imageBytes.length);
            return imageBytes;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}