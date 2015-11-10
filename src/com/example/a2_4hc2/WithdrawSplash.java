package com.example.a2_4hc2;

import java.util.Date;

import com.example.a2_4hc2.Account.AccountType;
import com.example.a2_4hc2.MainMenu.Action;
import com.example.a2_4hc2.Transaction.TransactionType;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WithdrawSplash extends Activity {
	Client currentClient;
	double amountToWithdraw;
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
		     amountToWithdraw = (Double) getIntent().getSerializableExtra("AmountWithdrew");
		     type = (Account.AccountType) getIntent().getSerializableExtra("AccountType");
		}
		
		// Execute some code after 10 seconds have passed
	    Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() { 
	         public void run() { 
	        	 if(type == Account.AccountType.CHEQUING){
	        		 currentClient.chequing.withdraw(amountToWithdraw);
	        		 currentClient.transactionHistory.add(new Transaction(amountToWithdraw, AccountType.CHEQUING, TransactionType.WITHDRAW, new Date(System.currentTimeMillis())));
        			 Toast.makeText(getApplicationContext(), "You withdrew $" + amountToWithdraw +
	        			 		" from your chequing account. The balance is now $"+ String.valueOf(currentClient.chequing.getBalance()) + 
	        			 		".", Toast.LENGTH_LONG).show();
        			 
        			
	        	 }
	        	 else{

        			 currentClient.savings.withdraw(amountToWithdraw);
        			 currentClient.transactionHistory.add(new Transaction(amountToWithdraw, AccountType.SAVINGS, TransactionType.WITHDRAW, new Date(System.currentTimeMillis())));
        			 Toast.makeText(getApplicationContext(), "You withdrew $" + amountToWithdraw +
	        			 		" from your savings account. The balance is now $"+ String.valueOf(currentClient.savings.getBalance()) + 
	        			 		".", Toast.LENGTH_LONG).show();
	        	 }
	        	 
	        	 AlertDialog.Builder builder = new AlertDialog.Builder(WithdrawSplash.this);
    			 builder.setMessage("Do you want to perform another task?").setPositiveButton("Yes", dialogClickListener)
    			     .setNegativeButton("No", dialogClickListener).show();
	         } 
	    }, 5000); 
		
	}
}
