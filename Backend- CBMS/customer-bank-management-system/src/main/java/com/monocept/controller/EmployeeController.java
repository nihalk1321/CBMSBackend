package com.monocept.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monocept.model.Customer;
import com.monocept.model.Employee;
import com.monocept.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empServ;

// CHANGE PASSWORD 
	@PutMapping(value = "/changeEmployeePassword/{employeeId}")
	public void changeEmployeePassword(@PathVariable int employeeId, String empNewPassword, String empOldPassword) {
		empServ.changeEmployeePasswordService(employeeId, empOldPassword, empNewPassword);
	}

//	update profile

	@GetMapping(value = "/getEmployeeById/{employeeId}")
	public Employee getEmployeeById(@PathVariable int employeeId) {
		return empServ.getEmployeeId(employeeId);
	}

	@PutMapping(value = "/editEmployee/{employeeId}", consumes = "application/json")
	public void editEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
		empServ.editEmployeeService(employeeId, employee);
	}

//CRUD OF CUSTOMERS
	@GetMapping("/getCustomers")
	public List<Customer> getCustomers() {
		List<Customer> customersList = empServ.getAllCustomersService();
		return customersList;
	}

	@PostMapping("/addCustomer")
	public void addCustomer(@RequestBody Customer customer) {
		System.out.println("added");
		empServ.addCustomerService(customer);
	}

	@PutMapping(value = "/editCustomer/{customerId}", consumes = "application/json")
	public void editCustomer(@PathVariable int customerId, @RequestBody Customer customer) {
		empServ.editCustomerService(customerId, customer);
	}

	@DeleteMapping(value = "/deleteCustomer/{customerId}")
	public void delelteCustomer(@PathVariable int customerId) {
		empServ.delelteCustomerService(customerId);
	}

//DEPOSIT AND WITHDRAW SERVICE
	@PostMapping(value = "/withdraw/{customerId}")
	public void withdrawAmount(@PathVariable int customerId, @RequestBody int amount) {
		System.out.println("Inside controller" + customerId);
		System.out.println("Inside controller" + amount);
		empServ.withdrawAmountService(customerId, amount);
	}

	@PostMapping(value = "/deposit/{customerId}")
	public void depositAmount(@PathVariable int customerId, @RequestBody int amount) {
		System.out.println("Inside controller" + customerId);
		System.out.println("Inside controller" + amount);
		empServ.depositAmountService(customerId, amount);
	}

//	CHANGE ACCOUNT STATUS

	@PostMapping(value = "/setCustomerStatusActive/{customerId}")
	public void changeCustomerAccountStatusActive(@PathVariable int customerId) {
		empServ.changeCustomerAccountStatusActiveService(customerId);
	}

	@PostMapping(value = "/setCustomerStatusInactive/{id}")
	public void changeCustomerAccountStatusInactive(@PathVariable int id) {
		empServ.changeCustomerAccountStatusInactiveService(id);
	}

	@PostMapping(value = "/setCustomerStatusLocked/{id}")
	public void changeCustomerAccountStatusLocked(@PathVariable int id) {
		empServ.changeCustomerAccountStatusLockedService(id);
	}

}