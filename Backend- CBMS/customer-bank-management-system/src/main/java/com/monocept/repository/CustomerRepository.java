package com.monocept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monocept.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//	Customer findByUsernameAndPassword(String Username, String Password);
	Customer findByCustomerUserNameAndCustomerPassword(String customerUserName,String customerPassword);

}
