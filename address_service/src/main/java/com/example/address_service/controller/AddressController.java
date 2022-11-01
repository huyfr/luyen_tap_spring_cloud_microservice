package com.example.address_service.controller;

import com.example.address_service.request.CreateAddressRequest;
import com.example.address_service.response.AddressResponse;
import com.example.address_service.service.AddressService;
import feign.FeignException;
import feign.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@PostMapping("/create")
	public AddressResponse createStudent (@RequestBody CreateAddressRequest createAddressRequest) {
		return addressService.createAddress(createAddressRequest);
	}
	
	@GetMapping("/getById/{id}")
	public AddressResponse getById (@PathVariable long id) throws FeignException {
//		throw new FeignException.NotFound(/**/"Item not found", request, request.body(), request.headers());
		return addressService.getById(id);
	}
	
}
