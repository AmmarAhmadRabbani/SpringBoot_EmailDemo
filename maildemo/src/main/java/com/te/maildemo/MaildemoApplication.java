package com.te.maildemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MaildemoApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(MaildemoApplication.class, args);
	}
	

    

}
