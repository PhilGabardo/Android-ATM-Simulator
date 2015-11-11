package com.a24hc3;

import java.util.Date;

import com.a24hc3.Account.AccountType;
import com.a24hc3.Transaction.TransactionType;
import com.a24hc3.R;
import com.a24hc3.R.layout;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

// Splash screen after withdrawing money
public class WithdrawSplash extends Activity {
	Client currentClient;
	Money amountToWithdraw;
	Account.AccountType type;
	DialogInterface.OnClickListener dialogClickListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.withdraw_splash);
		
        dialogClickListener = new DialogInterface.OnClickListener() {
			
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	Intent intent;
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		        	intent = new Intent(getBaseContext(), MainMenu.class);
		        	intent.putExtra("CurrentClient", currentClient);
		        	startActivity(intent);
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		        	intent = new Intent(getBaseContext(), MainMenu.class);
		        	startActivity(intent);
		            break;
		        }
		    }
		};
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
		     currentClient = (Client) getIntent().getSerializableExtra("CurrentClient");
		     amountToWithdraw = (Money) getIntent().getSerializableExtra("AmountWithdrew");
		     type = (Account.AccountType) getIntent().getSerializableExtra("AccountType");
		}
		
		// Execute after 10 seconds have passed
	    Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() { 
	         public void run() { 
	        	 String ActType = new String();
	        	 if(type == Account.AccountType.CHEQUING){
	        		 currentClient.chequing.withdraw(amountToWithdraw);
	        		 currentClient.transactionHistory.add(new Transaction(amountToWithdraw, AccountType.CHEQUING, TransactionType.WITHDRAW, new Date(System.currentTimeMillis())));
        			 ActType="Chequing";
        			
	        	 }
	        	 else{

        			 currentClient.savings.withdraw(amountToWithdraw);
        			 currentClient.transactionHistory.add(new Transaction(amountToWithdraw, AccountType.SAVINGS, TransactionType.WITHDRAW, new Date(System.currentTimeMillis())));
        			 ActType="Savings";
	        	 }
	        	 
	        	 AlertDialog.Builder builder = new AlertDialog.Builder(WithdrawSplash.this);
	        	 builder.setCancelable(false);
    			 builder.setMessage("You withdrew $" + amountToWithdraw.toString() +
     			 		" from your "+ActType+" account. The balance is now $"+ currentClient.savings.getBalance().toString() + 
     			 		".\n\nDo you want to perform another task?").setPositiveButton("Yes", dialogClickListener) 
    			     .setNegativeButton("No", dialogClickListener).show();
	         } 
	    }, 5000); 
		
	}
	
	@Override
	public void onBackPressed() {
	}
}
