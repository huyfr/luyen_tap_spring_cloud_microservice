package com.example.student_service.service;

import com.example.student_service.entity.Student;
import com.example.student_service.feign_client.AddressFeignClient;
import com.example.student_service.repository.StudentRepository;
import com.example.student_service.request.CreateStudentRequest;
import com.example.student_service.response.AddressResponse;
import com.example.student_service.response.StudentResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class StudentService {

	Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	AddressFeignClient addressFeignClient;

	@Autowired
	CommonService commonService;

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) throws HttpServerErrorException {
		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddress(createStudentRequest.getAddressId());
		student = studentRepository.save(student);
		StudentResponse response = new StudentResponse(student);
		response.setAddressResponse(commonService.getAddressById(student.getAddress()));
		return response;
	}
	
	public StudentResponse getById (long id) throws FeignException {
		Student student = studentRepository.findById(id).get();
		logger.info("Found student");
		StudentResponse response = new StudentResponse(student);
		response.setAddressResponse(commonService.getAddressById(student.getAddress()));
		logger.info("Call to address service successful");
		logger.info(response.getAddressResponse().toString());
		return response;
	}

/*	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById (long addressId) {
		AddressResponse response = addressFeignClient.getById(addressId);
		return response;
	}

	public AddressResponse fallbackGetAddressById (long addressId, Throwable t) {
		return new AddressResponse();
	}*/
}
