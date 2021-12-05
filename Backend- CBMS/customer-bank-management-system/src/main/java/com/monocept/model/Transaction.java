package com.monocept.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int transactionId;
	private Date transactionDate;
	@Enumerated(EnumType.STRING)
	private TransactionType type;

	@ManyToOne
	@JsonIgnore
	@JoinColumn
	private Customer customer;
	
	private long transferedToAccount;

	public Transaction(int transactionId, Date transactionDate, TransactionType type, Customer customer,long id) {
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.type = type;
		this.customer = customer;
	    this.transferedToAccount = id;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Transaction() {
		super();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
