package com.ml.springwithlombok.controllers;

import com.ml.springwithlombok.dto.RestContainer;
import com.ml.springwithlombok.services.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/rest/address/all")
    public RestContainer<?> listAddresses(){
        return addressService.findAddresses();
    }

    @GetMapping("/rest/address/search/city/{city}")
    public RestContainer<?> listAddressesByCity(@PathVariable("city")String city){
        return addressService.findAddressesByCity(city);
    }

    @GetMapping("/rest/address/search/state/{state}")
    public RestContainer<?> listAddressesByState(@PathVariable("state")String state){
        return addressService.findAddressesByState(state);
    }

}