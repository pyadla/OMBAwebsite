package com.ombawebsite.ItemFiles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customerId;
	private String accountNo;
	private String dateOfTransaction;
	private String timeOfTransaction;
	private String amount;
	private String toAccount;
	private String balance;
	private String toAccBalance;
	private String description;
	
	public Transaction(String customerId,String accountNo,String dateOfTransaction,
			String timeOfTransaction,String toAccount,String amount,String balance,
			String toAccBalance,String description) {
        this.customerId=customerId;
        this.accountNo=accountNo;
        this.dateOfTransaction=dateOfTransaction;
        this.timeOfTransaction=timeOfTransaction;
        this.toAccount=toAccount;
        this.amount=amount;
        this.balance=balance;
        this.toAccBalance=toAccBalance;
        this.description=description;
        
	}
		public Long getId() {
			return id;
		}
				
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		
		public String getDateOfTransaction() {
			return dateOfTransaction;
		}
		public void setDateOfTransaction(String dateOfTransaction) {
			this.dateOfTransaction = dateOfTransaction;
		}
		
		public String getTimeOfTransaction() {
			return timeOfTransaction;
		}
		public void setTimeOfTransaction(String timeOfTransaction) {
			this.timeOfTransaction = timeOfTransaction;
		}
		
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		
		public String getToAccount() {
			return toAccount;
		}
		public void setToAccount(String toAccount) {
			this.toAccount = toAccount;
		}

		public String getBalance() {
			return balance;
		}
		public void setBalance(String balance) {
			this.balance = balance;
		}
		
		public String getToAccBalance() {
			return toAccBalance;
		}
		public void setToAccBalance(String toAccBalance) {
			this.toAccBalance = toAccBalance;
		}
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

	}
