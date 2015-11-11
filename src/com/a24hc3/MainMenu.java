package com.a24hc3;


import com.a24hc3.R;
import com.a24hc3.R.id;
import com.a24hc3.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


// Main menu
public class MainMenu extends Activity {
	
	
	boolean loggedIn = false;
	Client currentClient;
	
	public enum Action {WITHDRAW, DEPOSIT, HISTORY, TRANSFER};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
		     currentClient = (Client) getIntent().getSerializableExtra("CurrentClient");
		     loggedIn = true;
		}
		
		TextView loggedInAs = (TextView) findViewById(R.id.login_status);
		
		
		if (loggedIn){
			loggedInAs.setText("Logged in as: " + currentClient.name + "\nChequing Balance: $" + currentClient.chequing.getBalance().toString() +
					"\nSavings Balance: $" + currentClient.savings.getBalance().toString());
		}
		else{
			loggedInAs.setText("Please choose an action to continue");
		}
		
		Button withdraw = (Button) findViewById(R.id.withdraw);
		Button deposit = (Button) findViewById(R.id.deposit);
		Button balanceHistory = (Button) findViewById(R.id.history);
		Button transfer = (Button) findViewById(R.id.transfer);
		
		withdraw.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 if (loggedIn){
		        	 Intent intent = new Intent(getBaseContext(), Withdraw.class); 
		        	 intent.putExtra("CurrentClient", currentClient);
		        	 startActivity(intent);
	        	 }
	        	 else{
	        		 Intent intent = new Intent(getBaseContext(), Login.class); 
	        		 intent.putExtra("Action", Action.WITHDRAW);
		        	 startActivity(intent);
	        	 }
	         }

	      });
		
		deposit.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 if (loggedIn){
		        	 Intent intent = new Intent(getBaseContext(), Deposit.class); 
		        	 intent.putExtra("CurrentClient", currentClient);
		        	 startActivity(intent);
	        	 }
	        	 else{
	        		 Intent intent = new Intent(getBaseContext(), Login.class); 
	        		 intent.putExtra("Action", Action.DEPOSIT);
		        	 startActivity(intent);
	        	 }
	         }

	      });
		
		balanceHistory.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 if (loggedIn){
		        	 Intent intent = new Intent(getBaseContext(), BalanceHistory.class); 
		        	 intent.putExtra("CurrentClient", currentClient);
		        	 startActivity(intent);
	        	 }
	        	 else{
	        		 Intent intent = new Intent(getBaseContext(), Login.class); 
	        		 intent.putExtra("Action", Action.HISTORY);
		        	 startActivity(intent);
	        	 }
	         }

	      });
		
		transfer.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 if (loggedIn){
		        	 Intent intent = new Intent(getBaseContext(), Transfer.class); 
		        	 intent.putExtra("CurrentClient", currentClient);
		        	 startActivity(intent);
	        	 }
	        	 else{
	        		 Intent intent = new Intent(getBaseContext(), Login.class); 
	        		 intent.putExtra("Action", Action.TRANSFER);
		        	 startActivity(intent);
	        	 }
	         }

	      });
		
	}
	
	@Override
	public void onBackPressed() {
	}


}
