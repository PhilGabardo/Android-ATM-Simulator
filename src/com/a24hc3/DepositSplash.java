package com.a24hc3;

import java.util.Date;

import com.a24hc3.Account.AccountType;
import com.a24hc3.Transaction.TransactionType;
import  com.a24hc3.R;
import  com.a24hc3.R.layout;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


// Splash screen after depositing money
public class DepositSplash extends Activity {
	Client currentClient;
	Money amount;
	Account.AccountType type;
	DialogInterface.OnClickListener dialogClickListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		setContentView(R.layout.deposit_splash);
		
		
        dialogClickListener = new DialogInterface.OnClickListener() {
			
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	Intent intent;
		    	
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		            //Yes button clicked
		        	intent = new Intent(getBaseContext(), MainMenu.class);
		        	intent.putExtra("CurrentClient", currentClient);
		        	startActivity(intent);
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		        	intent = new Intent(getBaseContext(), MainMenu.class);
		        	startActivity(intent);
		            break;
		        }
		    }
		};
		
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
		     currentClient = (Client) getIntent().getSerializableExtra("CurrentClient");
		     amount = (Money) getIntent().getSerializableExtra("AmountDeposited");
		     type = (Account.AccountType) getIntent().getSerializableExtra("AccountType");
		}
		
		// Execute some code after 10 seconds have passed
	    Handler handler = new Handler(); 
	
	    handler.postDelayed(new Runnable() { 
	         public void run() { 
	        	 String ActType = new String();
	        	 if(type == Account.AccountType.CHEQUING){
	        		 currentClient.chequing.deposit(amount);
	        		 currentClient.transactionHistory.add(new Transaction(amount, AccountType.CHEQUING, TransactionType.DEPOSIT, new Date(System.currentTimeMillis())));
	        		 ActType = "Chequing";
	        	 }
	        	 else{
	        		 currentClient.savings.deposit(amount);
	        		 currentClient.transactionHistory.add(new Transaction(amount, AccountType.SAVINGS, TransactionType.DEPOSIT, new Date(System.currentTimeMillis())));
	        		 ActType = "Savings";
	        	 }

	        	 
	        	 AlertDialog.Builder builder = new AlertDialog.Builder(DepositSplash.this);
	        	 builder.setCancelable(false);
    			 builder.setMessage("$" + amount.toString() + " was deposited into your "+ActType+" account.\n\nDo you want to perform another task?").setPositiveButton("Yes", dialogClickListener)
    			     .setNegativeButton("No", dialogClickListener).show();
	         } 
	    }, 5000); 
		
	}
	@Override
	public void onBackPressed() {
	}
	
}
