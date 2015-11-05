package com.example.a2_4hc2;

import com.example.a2_4hc2.Account.AccountType;
import java.io.Serializable;
import java.util.ArrayList;


public class Client implements Serializable{
	public Account chequing;
	public Account savings;
	public double ID;
	public String name;
	
	
	public Client(double uID, String uname) {
		ArrayList<Double> cActionHistory = new ArrayList<Double>();
		ArrayList<Double> sActionHistory = new ArrayList<Double>();
	    sActionHistory.add(4789.83);
	    sActionHistory.add( -9.0);
	    sActionHistory.add(6.94);
	    cActionHistory.add( 7859.39);
	    cActionHistory.add( -123.43);
	    cActionHistory.add(-5.09);
	    cActionHistory.add(-8.09);
	    cActionHistory.add( -2.09);
	    cActionHistory.add( -10.89);
	    cActionHistory.add(-150.08);
	    cActionHistory.add(-542.09);
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
