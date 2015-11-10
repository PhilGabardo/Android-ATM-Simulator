package com.example.a2_4hc2;

import com.example.a2_4hc2.Account.AccountType;
import com.example.a2_4hc2.Transaction.TransactionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Client implements Serializable{
	public Account chequing;
	public Account savings;
	public ArrayList<Transaction> transactionHistory;
	public double ID;
	public String name;
	long DAY_IN_MS = 1000 * 60 * 60 * 24;
	
	public Client(double uID, String uname) {
		transactionHistory = new ArrayList<Transaction>();
		ArrayList<Double> cActionHistory = new ArrayList<Double>();
		ArrayList<Double> sActionHistory = new ArrayList<Double>();

	    sActionHistory.add(4789.83);
	    transactionHistory.add(new Transaction(4789.83, AccountType.SAVINGS,TransactionType.DEPOSIT, new Date(System.currentTimeMillis() - (50 * DAY_IN_MS))));
	    sActionHistory.add( -9.0);
	    transactionHistory.add(new Transaction(9.0, AccountType.SAVINGS,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (47 * DAY_IN_MS))));
	    sActionHistory.add(6.94);
	    transactionHistory.add(new Transaction(6.94, AccountType.SAVINGS,TransactionType.DEPOSIT, new Date(System.currentTimeMillis() - (43 * DAY_IN_MS))));
	    cActionHistory.add( 7859.39);
	    transactionHistory.add(new Transaction(7859.39, AccountType.CHEQUING,TransactionType.DEPOSIT, new Date(System.currentTimeMillis() - (180 * DAY_IN_MS))));
	    cActionHistory.add( -123.43);
	    transactionHistory.add(new Transaction(123.43, AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (74 * DAY_IN_MS))));
	    cActionHistory.add(-5.09);
	    transactionHistory.add(new Transaction(5.09, AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (47 * DAY_IN_MS))));
	    cActionHistory.add(-8.09);
	    transactionHistory.add(new Transaction(8.09, AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (35 * DAY_IN_MS))));
	    cActionHistory.add( -2.09);
	    transactionHistory.add(new Transaction(2.09, AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (23 * DAY_IN_MS))));
	    cActionHistory.add( -10.89);
	    transactionHistory.add(new Transaction(10.89, AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (12 * DAY_IN_MS))));
	    cActionHistory.add(-150.08);
	    transactionHistory.add(new Transaction(150.08, AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (9 * DAY_IN_MS))));
	    cActionHistory.add(-542.09);
	    transactionHistory.add(new Transaction(542.09, AccountType.CHEQUING,TransactionType.WITHDRAW, new Date(System.currentTimeMillis() - (2 * DAY_IN_MS))));
	    chequing = new Account(AccountType.CHEQUING, cActionHistory);
	    savings = new Account(AccountType.SAVINGS, sActionHistory);
	    uID = ID;
	    name = uname;
	}
	
	public void transfer(boolean cheqToSav, double amount){
		if (cheqToSav){
			chequing.withdraw(amount);
			savings.deposit(amount);
		}
		else{
			savings.withdraw(amount);
			chequing.deposit(amount);
		}
		
	}
	
	
}
