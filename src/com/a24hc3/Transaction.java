package com.a24hc3;

import java.util.Date;
import java.io.Serializable;

import com.a24hc3.Account.AccountType;




// Bank transactions (withdraws and deposits)
public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum TransactionType {
	    DEPOSIT ("Deposit"), 
	    WITHDRAW ("Withdraw");
	    
	    
	    private final String name;       
	    
	    private TransactionType(String s) {
	        name = s;
	    }
	    
	    public String toString() {
	        return this.name;
	     }


	}
	
	public Money amount;
	public AccountType accountType;
	public TransactionType transactionType;
	public Date date;
	
	public Transaction(Money amount, AccountType accountType, TransactionType transactionType, Date date){
		this.amount = amount;
		this.accountType = accountType;
		this.transactionType = transactionType;
		this.date = date;
				
	}
}
