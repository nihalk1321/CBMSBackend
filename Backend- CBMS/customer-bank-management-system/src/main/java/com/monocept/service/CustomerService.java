package com.monocept.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monocept.model.Account;
import com.monocept.model.AccountStatusType;
import com.monocept.model.Customer;
import com.monocept.model.CustomerDTO;
import com.monocept.model.Payee;
import com.monocept.model.Transaction;
import com.monocept.model.TransactionType;
import com.monocept.repository.AccountRepository;
import com.monocept.repository.CustomerRepository;

@Transactional
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;

	public boolean checkStatus(int customerId) {
		Customer c = customerRepository.findById(customerId).get();
		if (c.getAccount().getStatus().equals(AccountStatusType.ACTIVE)) {
			return true;
		}
		return false;
	}

////  findBy id
//	public void authenticateUser(String username, String password) {
//		customerRepository.findByUsernameAndPassword(username, password);
//	}
	public String authenticationService(String customerUserName, String customerPassword) {
		Customer c = customerRepository.findByCustomerUserNameAndCustomerPassword(customerUserName, customerPassword);
		return Integer.toString(c.getCustomerId());
	}

//====CRUD SERVICE=========================================================================================

	public Customer getCustomerByIdService(int id) {
		Customer c = customerRepository.findById(id).get();
		return c;
	}

	public void addPayeeService(int id, Set<Payee> payees) {
		Customer c = customerRepository.getById(id);
		c.setPayees(payees);
		customerRepository.save(c);
	}

	public void editCustomerService(int id, CustomerDTO customerDto) {
		Customer c = customerRepository.findById(id).get();
		c.setCustomerEmail(customerDto.getCustomerDTOEmail());
//		c.setCustomerPassword(customerDto.getCustomerDTOPassword());
		c.setCustomerPhone(customerDto.getCustomerDTOPhone());
		c.setPayees(customerDto.getDTOPayees());

//    customerDto.setCustomerId(c.getCustomerId());
		customerRepository.save(c);
	}

	public String resetNewPassword(int custId, String oldpass, String newpass) {
		Customer c = customerRepository.getById(custId);
		if (oldpass.equals(newpass)) {
			return "Both passwords cannot be same";
		} else if (c.getCustomerPassword().equals(oldpass) && (oldpass != newpass)) {
			c.setCustomerPassword(newpass);
			return "Password changed successfully";

		}
		return "Account password is incorrect , password cannot be changed";

	}

//====SEND TO PAYEE=======================================================================================

	public void sendToPayeeService(int customerId, int payeeId, int amount) {

		Customer c = customerRepository.findById(customerId).get();

		Set<Payee> payeesList = c.getPayees();
		for (Payee p : payeesList) {
			if (p.getPayeeId() == payeeId) {
				Payee targetPayee = p;
				Account acc = accountRepository.findById(c.getAccount().getAccountId()).get();
				acc.withdraw(amount);

				Set<Transaction> txn = new HashSet<Transaction>();
				txn.add(new Transaction(0, new Date(), TransactionType.TRANSFER, c,
						targetPayee.getPayeeAccountNumber()));
				c.setTransactions(txn);

				customerRepository.save(c);
				return;
			}
		}
	}

	public Set<Payee> getAllPayeesService(Integer customerId) {
		Customer c = customerRepository.findById(customerId).get();

		return c.getPayees();
	}
}