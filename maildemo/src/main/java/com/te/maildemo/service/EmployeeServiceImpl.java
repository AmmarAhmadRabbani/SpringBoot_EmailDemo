package com.te.maildemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.te.maildemo.dto.EmployeeDTO;
import com.te.maildemo.entity.Employee;
import com.te.maildemo.exception.EmployeeNotFoundException;
import com.te.maildemo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Value("${spring.mail.username}")
	String from;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeDTO register(EmployeeDTO employeedto) {
		Optional<Employee> findById = employeeRepository.findById(employeedto.getEmpId());
		if (findById.isEmpty()) {
			Employee employee = new Employee();

			BeanUtils.copyProperties(employeedto, employee);
			Employee save = employeeRepository.save(employee);
			BeanUtils.copyProperties(save, employeedto);

		}
		return employeedto;
	}

	@Autowired
	private JavaMailSender mailSender;

	@Scheduled(cron = "0 * * * * ?")
	@Override
	public void sendEmail() {

		List<Employee> findAll = employeeRepository.findAll();
		for (Employee employee : findAll) {
			try {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
//				mailMessage.setFrom(from);
				mailMessage.setTo(employee.getEmail());
				if (employee.getGender().equalsIgnoreCase("male")) {
					mailMessage.setText("Dear Sir" + "\n" + "Welcome to technoelevate");

				} else {
					mailMessage.setText("Dear Mam" + "\n" + "Welcome to technoelevate");
				}
				mailMessage.setSubject("My first mail" + employee.getEname());
				mailSender.send(mailMessage);
				System.out.println("mail send successfully");
			} catch (MailException e) {
				System.out.println("Error while sending mail");
			}
		}
	}

	@Override
	public EmployeeDTO getEmployee(Integer empId) {

		Optional<Employee> findByEmpId = employeeRepository.findById(empId);
		if (findByEmpId.isPresent()) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			BeanUtils.copyProperties(findByEmpId.get(), employeeDTO);
			return employeeDTO;
		}

		throw new EmployeeNotFoundException("invalid employee id");
	}

}
