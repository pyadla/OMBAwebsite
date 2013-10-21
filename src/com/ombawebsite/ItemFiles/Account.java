package com.ombawebsite.ItemFiles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
  

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNo;
    private String customerId;
    private String balance;    
    private String lastStatementDate;    
    private String lastTransactionDate;
    private String creationDate;


    // Accessors for the fields. JPA doesn't use these, but your application does.
    public Account(String accountNo,String customerId,String balance,
    		String lastStatementDate,String lastTransactionDate,
    		String creationDate)  
    { 
        this.accountNo = accountNo;  
        this.customerId = customerId;  
        this.balance = balance;  
        this.lastTransactionDate=lastTransactionDate;
        this.lastStatementDate=lastStatementDate;
        this.creationDate=creationDate;
    }  

   
    public Long getId() {
		return id;
	}
	
    public String getAccountNo() {
        return accountNo;
    } 
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    } 

    public String getCustomerId() {
        return customerId;
    } 
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    } 

    public String getBalance() {
        return balance;
    } 
    public void setBalance(String balance) {
        this.balance = balance;
    } 
    
    public String getLastStatementDate() {
        return lastStatementDate;
    } 
    public void setLastStatementDate(String lastStatementDate) {
        this.lastStatementDate = lastStatementDate;
    } 
    public String getLastTransactionDate() {
        return lastTransactionDate;
    } 
    public void setLastTransactionDate(String lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }    
        
    public String getCreationDate() {
        return creationDate;
    } 
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    } 
    
}

