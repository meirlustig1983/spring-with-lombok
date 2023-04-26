package com.ml.springwithlombok.repositories;

import com.ml.springwithlombok.dao.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAll();
    List<Address> findByCity(String city);
    List<Address> findByState(String state);
}
