package com.ml.springwithlombok.services;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dto.AddressDto;
import com.ml.springwithlombok.dto.RestContainer;
import com.ml.springwithlombok.repositories.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public RestContainer<?> findAddresses() {
        LOGGER.info("AddressService.findAddresses() - retrieving all addresses");
        final List<Address> allAddresses = addressRepository.findAll();

        if (allAddresses.isEmpty()) {
            return new RestContainer<>("No Records Found", "Address");
        } else {
            return new RestContainer<>(addressListConverter(allAddresses), "Address");
        }
    }

    public RestContainer<?> findAddressesByCity(String city) {
        LOGGER.info("AddressService.findAddressesByCity(...) - retrieving all addresses by city");
        final List<Address> cityAddresses = addressRepository.findByCity(city);

        if (cityAddresses.isEmpty()) {
            return new RestContainer<>("No Records Found", "Address");
        } else {
            return new RestContainer<>(addressListConverter(cityAddresses), "Address");
        }
    }

    public RestContainer<?> findAddressesByState(String state) {
        LOGGER.info("AddressService.findAddressesByState(...) - retrieving all addresses by state");
        final List<Address> stateAddresses = addressRepository.findByState(state);

        if (stateAddresses.isEmpty()) {
            return new RestContainer<>("No Records Found", "Address");
        } else {
            return new RestContainer<>(addressListConverter(stateAddresses), "Address");
        }
    }

    private List<AddressDto> addressListConverter(List<Address> addresses) {
        LOGGER.info("AddressService.addressConverter - converting Address Entity to Address DTO");
        List<AddressDto> addressDtos = new ArrayList<>();
        for (Address address : addresses) {
            addressDtos.add(
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
        return addressDtos;
    }
}