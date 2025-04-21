package com.te.maildemo.service;

import com.te.maildemo.dto.EmployeeDTO;

public interface EmployeeService {

	public EmployeeDTO register(EmployeeDTO employeedto);

	
	public void sendEmail();

	public EmployeeDTO getEmployee(Integer empId);

}
