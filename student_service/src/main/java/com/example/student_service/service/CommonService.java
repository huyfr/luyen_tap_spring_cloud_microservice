package com.example.student_service.service;

import com.example.student_service.feign_client.AddressFeignClient;
import com.example.student_service.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    Logger log = LoggerFactory.getLogger(CommonService.class);

    long count = 1;

    @Autowired
    AddressFeignClient addressFeignClient;

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById (long addressId) {
        log.info("Count = " + count);
        count++;
        AddressResponse response = addressFeignClient.getById(addressId);
        return response;
    }

    public AddressResponse fallbackGetAddressById (long addressId, Throwable t) {
        log.error("ERROR = " + t);
        return new AddressResponse();
    }
}
