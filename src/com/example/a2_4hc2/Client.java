package com.example.a2_4hc2;

import com.example.a2_4hc2.Account.AccountType;
import java.io.Serializable;
import java.util.ArrayList;


public class Client implements Serializable{
	public Account chequing;
	public Account savings;
	public int ID;
	public String name;
	
	
	public Client(int uID, String uname) {
		ArrayList<Float> cActionHistory = new ArrayList<Float>();
		ArrayList<Float> sActionHistory = new ArrayList<Float>();
	    sActionHistory.add((float) 4789.83);
	    sActionHistory.add((float) -9.0);
	    sActionHistory.add((float) 6.94);
	    cActionHistory.add((float) 7859.39);
	    cActionHistory.add((float) -123.43);
	    cActionHistory.add((float) -5.09);
	    cActionHistory.add((float) -8.09);
	    cActionHistory.add((float) -2.09);
	    cActionHistory.add((float) -10.89);
	    cActionHistory.add((float) -150.08);
	    cActionHistory.add((float) -542.09);
	    chequing = new Account(AccountType.CHEQUING, cActionHistory);
	    savings = new Account(AccountType.SAVINGS, sActionHistory);
	    uID = ID;
	    name = uname;
	}
	
	
}
