package com.a24hc3;

import java.util.ArrayList;
import java.io.Serializable;

// Bank account
public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum AccountType {
	    CHEQUING ("Chequings"), SAVINGS ("Savings");
	    
	    
	    private final String name;       
	    
	    private AccountType(String s) {
	        name = s;
	    }
	    
	    public String toString() {
	        return this.name;
	     }

	    
	}
	
	// Keep track of deposits/withdraws
	private ArrayList<Money> actionHistory;
	private AccountType aType;

	public Account( AccountType accType, ArrayList<Money> aHistory) {
	    aType = accType;
	    actionHistory = aHistory;
	}

	public void deposit (Money u_amm){
	    actionHistory.add(u_amm);
	}

	public Money withdraw(Money amount) {
	    actionHistory.add(new Money(0-amount.amount));
	    return getBalance();
	}

	public Money getBalance() {
		float balance = 0;
		for (int i = 0 ; i < actionHistory.size(); i++){
			balance += actionHistory.get(i).amount;
		}
	    return new Money(balance);
	}
	public AccountType getAccountType(){
	    return aType;
	}

}
