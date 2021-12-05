package com.monocept.customer.bank.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.monocept" })
@EntityScan("com.monocept")
@EnableJpaRepositories({ "com.monocept.repository" })
public class CustomerBankManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerBankManagementSystemApplication.class, args);
	System.out.println("main run");
	}

}
