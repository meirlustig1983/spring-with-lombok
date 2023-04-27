package com.ml.springwithlombok.controllers;

import com.ml.springwithlombok.dto.AddressDto;
import com.ml.springwithlombok.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/api/v1/addresses"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<List<AddressDto>> listAddresses() {
        final List<AddressDto> addresses = addressService.findAddresses();
        if (addresses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(addresses);
    }

    @GetMapping("/search/{city}/city")
    public ResponseEntity<List<AddressDto>> listAddressesByCity(@PathVariable("city") String city) {
        final List<AddressDto> addressesByCity = addressService.findAddressesByCity(city);
        if (addressesByCity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(addressesByCity);
    }

    @GetMapping("/search/{state}/state")
    public ResponseEntity<List<AddressDto>> listAddressesByState(@PathVariable("state") String state) {
        final List<AddressDto> addressesByState = addressService.findAddressesByState(state);
        if (addressesByState.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(addressesByState);
    }

}