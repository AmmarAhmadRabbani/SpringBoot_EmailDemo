package com.te.maildemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class EmployeeNotFoundException extends RuntimeException {

	private final String message;
}
