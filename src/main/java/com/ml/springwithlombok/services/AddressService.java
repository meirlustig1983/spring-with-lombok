package com.ml.springwithlombok.services;

import com.ml.springwithlombok.dao.Address;
import com.ml.springwithlombok.dto.AddressDto;
import com.ml.springwithlombok.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ml.springwithlombok.services.EmployeeService.getAddressDtoList;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public List<AddressDto> findAddresses() {
        log.info("AddressService.findAddresses() - retrieving all addresses");
        val allAddresses = addressRepository.findAll();
        return addressListConverter(allAddresses);
    }

    public List<AddressDto> findAddressesByCity(String city) {
        log.info("AddressService.findAddressesByCity(...) - retrieving all addresses by city. value: {}", city);
        val cityAddresses = addressRepository.findByCity(city);
        return addressListConverter(cityAddresses);
    }

    public List<AddressDto> findAddressesByState(String state) {
        log.info("AddressService.findAddressesByState(...) - retrieving all addresses by state. value: {}", state);
        val stateAddresses = addressRepository.findByState(state);
        return addressListConverter(stateAddresses);
    }

    private List<AddressDto> addressListConverter(List<Address> addresses) {
        log.info("AddressService.addressConverter - converting Address Entity to Address DTO");
        return getAddressDtoList(addresses);
    }
}