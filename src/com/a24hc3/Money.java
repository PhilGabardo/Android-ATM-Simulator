package com.a24hc3;

import java.io.Serializable;
import java.text.DecimalFormat;


// Represent money. Print amount as double with 2 decimal points.
public class Money implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double amount;
	
	public Money(double amount){
		this.amount = amount;
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.00"); 
		return df.format(this.amount);
	}

}
