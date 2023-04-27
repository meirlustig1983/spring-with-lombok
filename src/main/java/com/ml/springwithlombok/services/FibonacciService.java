package com.ml.springwithlombok.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class FibonacciService {

    @Getter(lazy = true)
    private final List<BigInteger> fibonacciNumbers = calculateFibonacciSequence();

    private List<BigInteger> calculateFibonacciSequence() {
        log.info("FibonacciService.calculateFibonacciSequence() - starting");
        List<BigInteger> result = new LinkedList<>();
        result.add(BigInteger.ZERO);
        result.add(BigInteger.ONE);

        int limit = 10000;
        for (int i = 2; i < limit; i++) {
            result.add(result.get(i - 1).add(result.get(i - 2)));
        }
        log.info("FibonacciService.calculateFibonacciSequence() - completed");
        return result;
    }
}
