package com.monocept.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int accountId;
	@Column(unique = true)
	private long accountNumber;
	private double accountBalance;
	@Enumerated(EnumType.STRING)
	private AccountStatusType status;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public long getAccountNumber() {

		return accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public AccountStatusType getStatus() {
		return status;
	}

	public void setStatus(AccountStatusType status) {
		this.status = status;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
	}

	public void withdraw(int amount) {
		System.out.println("inisde account withdaarw");
		if ((this.accountBalance - amount) > 500) {
			this.setAccountBalance(this.accountBalance - amount);
			return;
		}
		return;
	}

	public void deposit(int amount) {
		this.setAccountBalance(this.accountBalance + amount);
	}

}
