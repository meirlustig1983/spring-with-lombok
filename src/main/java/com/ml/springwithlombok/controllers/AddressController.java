package com.ml.springwithlombok.controllers;

import com.ml.springwithlombok.dto.AddressDto;
import com.ml.springwithlombok.services.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/api/v1/addresses"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<List<AddressDto>> listAddresses() {
        log.info("AddressController.listAddresses() - return all addresses");
        val addresses = addressService.findAddresses();
        if (addresses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(addresses);
    }

    @GetMapping("/search/{city}/city")
    public ResponseEntity<List<AddressDto>> listAddressesByCity(@PathVariable("city") String city) {
        log.info("AddressController.listAddressesByCity(...) - return all addresses by city. value: {}", city);
        val addressesByCity = addressService.findAddressesByCity(city);
        if (addressesByCity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(addressesByCity);
    }

    @GetMapping("/search/{state}/state")
    public ResponseEntity<List<AddressDto>> listAddressesByState(@PathVariable("state") String state) {
        log.info("AddressController.listAddressesByState(...) - return all addresses by state. value: {}", state);
        val addressesByState = addressService.findAddressesByState(state);
        if (addressesByState.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(addressesByState);
    }

}