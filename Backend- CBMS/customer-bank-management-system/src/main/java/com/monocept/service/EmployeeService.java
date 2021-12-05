package com.monocept.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monocept.model.Account;
import com.monocept.model.AccountStatusType;
import com.monocept.model.Customer;
import com.monocept.model.Employee;
import com.monocept.model.Transaction;
import com.monocept.model.TransactionType;
import com.monocept.repository.AccountRepository;
import com.monocept.repository.CustomerRepository;
import com.monocept.repository.EmployeeRepository;
import com.monocept.repository.TransactionRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private EmailService email;

//get all customers service
	public List<Customer> getAllCustomersService() {
		List<Customer> dtoList = new ArrayList<Customer>();
		dtoList = customerRepository.findAll();

		return dtoList;
	}

	// ADD customer SERVICE
	public void addCustomerService(Customer customer) {
		customerRepository.save(customer);
		System.out.println(customer.getCustomerEmail());
		email.sendMail(customer.getCustomerEmail(), customer.getCustomerId());
	}

	// edit customer SERVICE
	public void editCustomerService(int id, Customer customer) {

		Customer updatedCustomer = customerRepository.findById(id).get();
		updatedCustomer = customer;
		customerRepository.save(updatedCustomer);
	}

	// delete customer SERVICE
	public void delelteCustomerService(int id) {
		Customer c = customerRepository.findById(id).get();
		customerRepository.delete(c);
	}
//	CHANGE CUSTOMER STATUS

	public void changeCustomerAccountStatusActiveService(int id) {
		Customer c = customerRepository.findById(id).get();
		c.getAccount().setStatus(AccountStatusType.ACTIVE);
		customerRepository.save(c);
	}

	public void changeCustomerAccountStatusInactiveService(int id) {
		Customer c = customerRepository.findById(id).get();
		c.getAccount().setStatus(AccountStatusType.INACTIVE);
		customerRepository.save(c);
	}

	public void changeCustomerAccountStatusLockedService(int id) {
		Customer c = customerRepository.findById(id).get();
		c.getAccount().setStatus(AccountStatusType.LOCKED);
		customerRepository.save(c);
	}

//DEPOSIT AND WITHDRAW
	public void withdrawAmountService(int id, int amount) {
		Customer customer = customerRepository.findById(id).get();

		Account acc = accountRepository.findById(customer.getAccount().getAccountId()).get();
		acc.withdraw(amount);

		Set<Transaction> t = new HashSet<Transaction>();
		t.add(new Transaction(0, new Date(), TransactionType.WITHDRAW, customer,
				customer.getAccount().getAccountNumber()));
		customer.setTransactions(t);

		customerRepository.save(customer);
	}

	public void depositAmountService(int id, int amount) {
		Customer customer = customerRepository.findById(id).get();

		Account acc = accountRepository.findById(customer.getAccount().getAccountId()).get();
		acc.deposit(amount);

		Set<Transaction> t = new HashSet<Transaction>();
		t.add(new Transaction(0, new Date(), TransactionType.DEPOSIT, customer,
				customer.getAccount().getAccountNumber()));
		customer.setTransactions(t);

		customerRepository.save(customer);
	}

//EMPLOYEE CHANGES
	public void changeEmployeePasswordService(int employeeId, String empOldPassword, String empNewPassword) {
		Employee e = employeeRepository.findById(employeeId).get();
		System.out.println(e.getEmployeePassword());
		if (e.getEmployeePassword().equals(empOldPassword)) {
			e.setEmployeePassword(empNewPassword);
		}
		employeeRepository.save(e);

	}

	public void editEmployeeService(int employeeId, Employee employee) {
		Employee updatedEmployee = employeeRepository.findById(employeeId).get();
		updatedEmployee = employee;
		employeeRepository.save(updatedEmployee);
	}

	public Employee getEmployeeId(int employeeId) {
		Employee e = employeeRepository.findById(employeeId).get();
		return e;
	}

}
