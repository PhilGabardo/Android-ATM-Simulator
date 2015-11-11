package com.a24hc3;

import com.a24hc3.Account.AccountType;
import com.a24hc3.R;
import com.a24hc3.R.id;
import com.a24hc3.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


// Deposit money into an account
public class Deposit extends Activity {
	
	Client currentClient;
	EditText depositAmount;
	RadioButton chequingDeposit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deposit);
		depositAmount = (EditText) findViewById(R.id.deposit_amount);
		Button back = (Button) findViewById(R.id.deposit_back);
		Button submit = (Button) findViewById(R.id.deposit_submit);
		chequingDeposit = (RadioButton) findViewById(R.id.chequing_deposit);
		 
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
		     currentClient = (Client) getIntent().getSerializableExtra("CurrentClient");
		}
		
		back.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
		        	 Intent intent = new Intent(getBaseContext(), MainMenu.class); 
		        	 intent.putExtra("CurrentClient", currentClient);
		        	 startActivity(intent);
	         }

	      });
		
		submit.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 	Money amountToDeposit;
	        	 	if(depositAmount.getText().toString().matches("")){
	        	 		 Toast.makeText(getApplicationContext(), "Please enter a value you wish to deposit",
		        				 Toast.LENGTH_LONG).show();
	        	 	}
	        	 	
	        	 	else{
	        	 	
		        	 	amountToDeposit = new Money(Double.parseDouble(depositAmount.getText().toString()));
		        	 	
		        	 	Intent intent = new Intent(getBaseContext(), DepositSplash.class);
		        	 	if (chequingDeposit.isChecked()){
		        	 	    intent.putExtra("AccountType", AccountType.CHEQUING);
		        	 	}
		        	 	else{
		        	 	    intent.putExtra("AccountType", AccountType.SAVINGS);
		        	 	 }
			        	  
			        	 intent.putExtra("CurrentClient", currentClient);
			        	 intent.putExtra("AmountDeposited", amountToDeposit);
			        	 startActivity(intent);
	        	 	}
	         }

	      });
		
		
	}
	
	@Override
	public void onBackPressed() {
	}
}
