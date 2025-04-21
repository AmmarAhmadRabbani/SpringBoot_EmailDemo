package com.te.maildemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	
	private Integer empId;
	private String ename;
	private Integer eAge;
	private String gender;
	private String email;
	private String password;

}
