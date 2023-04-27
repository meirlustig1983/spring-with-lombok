package com.ml.springwithlombok.services;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dto.AddressDto;
import com.ml.springwithlombok.dto.RestContainer;
import com.ml.springwithlombok.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ml.springwithlombok.services.EmployeeService.getAddressDtoList;

@RequiredArgsConstructor
@Service
public class AddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;

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
        return getAddressDtoList(addresses);
    }
}