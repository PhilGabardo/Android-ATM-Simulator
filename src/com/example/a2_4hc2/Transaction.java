package com.example.a2_4hc2;

import java.util.Date;
import java.io.Serializable;


import com.example.a2_4hc2.Account.AccountType;



public class Transaction implements Serializable{
	
	public enum TransactionType {
	    DEPOSIT, WITHDRAW
	}
	
	public double amount;
	public AccountType accountType;
	public TransactionType transactionType;
	public Date date;
	
	public Transaction(double amount, AccountType accountType, TransactionType transactionType, Date date){
		this.amount = amount;
		this.accountType = accountType;
		this.transactionType = transactionType;
		this.date = date;
				
	}
}