package com.example.a2_4hc2;

import java.util.ArrayList;
import java.io.Serializable;


public class Account implements Serializable{
	
	public enum AccountType {
	    CHEQUING, SAVINGS
	}
	
	private ArrayList<Double> actionHistory;
	private AccountType aType;

	public Account( AccountType accType, ArrayList<Double> aHistory) {
	    aType = accType;
	    actionHistory = aHistory;
	}

	public void deposit (double u_amm){
	    actionHistory.add(u_amm);
	}

	public double withdraw(double amount) {
	    actionHistory.add(0-amount);
	    return getBalance();
	}

	public double getBalance() {
		float balance = 0;
		for (int i = 0 ; i < actionHistory.size(); i++){
			balance += actionHistory.get(i);
		}
	    return balance;
	}
	public AccountType getAccountType(){
	    return aType;
	}

}
