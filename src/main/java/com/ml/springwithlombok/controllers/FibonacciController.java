package com.ml.springwithlombok.controllers;

import com.ml.springwithlombok.services.FibonacciService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/api/v1/fibonacci"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class FibonacciController {

    private final FibonacciService fibonacciService;
    @GetMapping("/all")
    public ResponseEntity<List<BigInteger>> listFibonacciSequence(){
        log.info("FibonacciController.listFibonacciSequence() - starting");
        List<BigInteger> fibonacciNumbers = fibonacciService.getFibonacciNumbers();
        if(fibonacciNumbers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        log.info("FibonacciController.listFibonacciSequence() - complete");
        return ResponseEntity.ok().body(fibonacciNumbers);
    }
}
