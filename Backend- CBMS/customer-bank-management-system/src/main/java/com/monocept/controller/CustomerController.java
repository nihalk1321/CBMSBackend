package com.monocept.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.model.CredentialsDTO;
import com.monocept.model.Customer;
import com.monocept.model.CustomerDTO;
import com.monocept.model.Payee;
import com.monocept.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

//  authenticate

	@PostMapping(value = "/authenticate")
	public String Authentication(@RequestBody CredentialsDTO credentials) {
		System.out.println(credentials);
		System.out.println(credentials.getPasswordDTO());
		System.out.println(credentials.getUserNameDTO());
		return customerService.authenticationService(credentials.getUserNameDTO(), credentials.getPasswordDTO());

	}

	@GetMapping(value = "/getAccountStatus/{customerId}")
	public boolean getAccountStatus(@PathVariable Integer customerId) {
		boolean status = customerService.checkStatus(customerId);
		return status;
	}

//====CRUD OPERATION================================================================================================================================

	@GetMapping(value = "/getCustomerDetails/{customerId}")
	public Customer getCustomerById(@PathVariable Integer customerId) {
		return customerService.getCustomerByIdService(customerId);
	}

	@PostMapping(value = "/addPayee/{customerId}")
	public void addPayee(@PathVariable int customerId, @RequestBody Set<Payee> payees) {
		customerService.addPayeeService(customerId, payees);
	}

	@PutMapping(value = "/editCustomer/{customerId}")
	public void editCustomer(@PathVariable Integer customerId, @RequestBody CustomerDTO customerDto) {
		customerService.editCustomerService(customerId, customerDto);
	}

	@PostMapping(value = "/resetPassword/{customerId}")
	public void resetPassword(@PathVariable Integer customerId, String oldpass, String newpass) {
		customerService.resetNewPassword(customerId, oldpass, newpass);
	}

//====SEND TO PAYEE=======================================================================================

	@PostMapping(value = "{customerId}/sendToPayee/{payeeId}")
	public void sendToPayee(@PathVariable int customerId, @PathVariable int payeeId, @RequestBody int amount) {
		customerService.sendToPayeeService(customerId, payeeId, amount);
	}

	@GetMapping(value = "/getAllPayees/{customerId}")
	public Set<Payee> getAllPayees(@PathVariable Integer customerId) {
		return customerService.getAllPayeesService(customerId);
	}

}