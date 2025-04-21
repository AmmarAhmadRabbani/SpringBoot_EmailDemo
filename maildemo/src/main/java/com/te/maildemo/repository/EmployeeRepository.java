package com.te.maildemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.maildemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
