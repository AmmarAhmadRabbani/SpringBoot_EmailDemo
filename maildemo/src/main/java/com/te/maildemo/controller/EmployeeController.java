package com.te.maildemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.maildemo.dto.EmailDTO;
import com.te.maildemo.dto.EmployeeDTO;
import com.te.maildemo.dto.ResponseDTO;
import com.te.maildemo.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> register(@RequestBody EmployeeDTO employeedto) {
		return ResponseEntity
				.ok(new ResponseDTO(false, "Successfully registered", employeeService.register(employeedto)));
	}

	@GetMapping("/getEmployeeById/{empId}")
	public ResponseEntity<ResponseDTO> getEmployeeById(@PathVariable Integer empId) {
		EmployeeDTO userById = employeeService.getEmployee(empId);
		if (userById != null) {
			return new ResponseEntity<>(new ResponseDTO(false, "get successfully", userById), HttpStatus.OK);

		}
		return new ResponseEntity<>(new ResponseDTO(true, "invalid user id", null), HttpStatus.BAD_REQUEST);

	}


	@PostMapping("/send")
	public ResponseEntity<String> sendEmail() {
		employeeService.sendEmail();
		return ResponseEntity.ok("success");
	}

}
