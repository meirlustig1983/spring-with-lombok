package com.ml.springwithlombok.services;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dto.AddressDto;
import com.ml.springwithlombok.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
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

    public List<AddressDto> findAddresses() {
        LOGGER.info("AddressService.findAddresses() - retrieving all addresses");
        val allAddresses = addressRepository.findAll();
        return addressListConverter(allAddresses);
    }

    public List<AddressDto> findAddressesByCity(String city) {
        LOGGER.info("AddressService.findAddressesByCity(...) - retrieving all addresses by city. value: {}", city);
        val cityAddresses = addressRepository.findByCity(city);
        return addressListConverter(cityAddresses);
    }

    public List<AddressDto> findAddressesByState(String state) {
        LOGGER.info("AddressService.findAddressesByState(...) - retrieving all addresses by state. value: {}", state);
        val stateAddresses = addressRepository.findByState(state);
        return addressListConverter(stateAddresses);
    }

    private List<AddressDto> addressListConverter(List<Address> addresses) {
        LOGGER.info("AddressService.addressConverter - converting Address Entity to Address DTO");
        return getAddressDtoList(addresses);
    }
}