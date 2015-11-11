package com.a24hc3;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.a24hc3.Account.AccountType;
import com.a24hc3.Transaction.TransactionType;

// Actual bank user
public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Account chequing;
	public Account savings;
	public ArrayList<Transaction> transactionHistory;
	public double ID;
	public String name;
	long DAY_IN_MS = 1000 * 60 * 60 * 24;
	
	public Client(double uID, String uname) {
		transactionHistory = new ArrayList<Transaction>();
		ArrayList<Money> cActionHistory = new ArrayList<Money>();
		ArrayList<Money> sActionHistory = new ArrayList<Money>();
		
		// Create history of transactions
	    sActionHistory.add(new Money(4789.83));
	    transactionHistory.add(new Transaction(new Money(4789.83), AccountType.SAVINGS,TransactionType.DEPOSIT, new Date(System.currentTimeMillis() - (50 * DAY_IN_MS))));
	    sActionHistory.add(new Money(-9.0));
	    transactionHistory.add(new Transaction(new Money(9.0), AccountType.SAVINGS,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (47 * DAY_IN_MS))));
	    sActionHistory.add(new Money(6.94));
	    transactionHistory.add(new Transaction(new Money(6.94), AccountType.SAVINGS,TransactionType.DEPOSIT, new Date(System.currentTimeMillis() - (43 * DAY_IN_MS))));
	    cActionHistory.add(new Money(7859.39));
	    transactionHistory.add(new Transaction(new Money(7859.39), AccountType.CHEQUING,TransactionType.DEPOSIT, new Date(System.currentTimeMillis() - (180 * DAY_IN_MS))));
	    cActionHistory.add(new Money(-123.43));
	    transactionHistory.add(new Transaction(new Money(123.43), AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (74 * DAY_IN_MS))));
	    cActionHistory.add(new Money(-5.09));
	    transactionHistory.add(new Transaction(new Money(5.09), AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (47 * DAY_IN_MS))));
	    cActionHistory.add(new Money(-8.09));
	    transactionHistory.add(new Transaction(new Money(8.09), AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (35 * DAY_IN_MS))));
	    cActionHistory.add(new Money(-2.09));
	    transactionHistory.add(new Transaction(new Money(2.09), AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (23 * DAY_IN_MS))));
	    cActionHistory.add(new Money(-10.89));
	    transactionHistory.add(new Transaction(new Money(10.89), AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (12 * DAY_IN_MS))));
	    cActionHistory.add(new Money(-150.08));
	    transactionHistory.add(new Transaction(new Money(150.08), AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (9 * DAY_IN_MS))));
	    cActionHistory.add(new Money(-542.09));
	    transactionHistory.add(new Transaction(new Money(542.09), AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (2 * DAY_IN_MS))));
	    chequing = new Account(AccountType.CHEQUING, cActionHistory);
	    savings = new Account(AccountType.SAVINGS, sActionHistory);
	    uID = ID;
	    name = uname;
	}
	
	// transfer money from chequings to savings, or vice versa
	public void transfer(boolean cheqToSav, Money amount){
		if (cheqToSav){
			chequing.withdraw(amount);
			savings.deposit(amount);
			transactionHistory.add(new Transaction(amount, AccountType.CHEQUING, TransactionType.WITHDRAW, new Date(System.currentTimeMillis())));
			transactionHistory.add(new Transaction(amount, AccountType.SAVINGS, TransactionType.DEPOSIT, new Date(System.currentTimeMillis())));
		}
		else{
			savings.withdraw(amount);
			chequing.deposit(amount);
			transactionHistory.add(new Transaction(amount, AccountType.CHEQUING, TransactionType.DEPOSIT, new Date(System.currentTimeMillis())));
			transactionHistory.add(new Transaction(amount, AccountType.SAVINGS, TransactionType.WITHDRAW, new Date(System.currentTimeMillis())));
		}
		
	}
	
	
}
