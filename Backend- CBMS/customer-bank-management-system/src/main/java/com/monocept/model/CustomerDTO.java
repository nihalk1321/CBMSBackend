package com.monocept.model;

import java.util.HashSet;
import java.util.Set;

public class CustomerDTO {

	private String customerDTOEmail;
	private long customerDTOPhone;
	private String customerDTOPassword;
	private Set<Payee> DTOPayees = new HashSet<>();

//  public CustomerDTO(String customerDTOEmail, long customerDTOPhone, String customerDTOPassword,
//      Set<Payee> dTOPayees) {
//    super();
//    this.customerDTOEmail = customerDTOEmail;
//    this.customerDTOPhone = customerDTOPhone;
//    this.customerDTOPassword = customerDTOPassword;
//    DTOPayees = dTOPayees;
//  }

	public CustomerDTO convertToDTO(Customer customer) {
		CustomerDTO customerDto = new CustomerDTO();

		customerDto.setCustomerDTOEmail(customer.getCustomerEmail());
		customerDto.setCustomerDTOPassword(customer.getCustomerPassword());
		customerDto.setCustomerDTOPhone(customer.getCustomerPhone());
		customerDto.setDTOPayees(customer.getPayees());

		return customerDto;
	}

	public String getCustomerDTOEmail() {
		return customerDTOEmail;
	}

	public void setCustomerDTOEmail(String customerDTOEmail) {
		this.customerDTOEmail = customerDTOEmail;
	}

	public long getCustomerDTOPhone() {
		return customerDTOPhone;
	}

	public void setCustomerDTOPhone(long customerDTOPhone) {
		this.customerDTOPhone = customerDTOPhone;
	}

	public String getCustomerDTOPassword() {
		return customerDTOPassword;
	}

	public void setCustomerDTOPassword(String customerDTOPassword) {
		this.customerDTOPassword = customerDTOPassword;
	}

	public Set<Payee> getDTOPayees() {
		return DTOPayees;
	}

	public void setDTOPayees(Set<Payee> dTOPayees) {
		DTOPayees = dTOPayees;
	}

}