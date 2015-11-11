package com.a24hc3;

import com.a24hc3.MainMenu.Action;
import  com.a24hc3.R;
import  com.a24hc3.R.id;
import  com.a24hc3.R.layout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;


// Login screen
public class Login extends Activity {
	
	boolean submitEnabled = false;
	Action action;
	EditText accNumberText;
	Button submit;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		
		submit = (Button) findViewById(R.id.submit);
		accNumberText = (EditText) findViewById(R.id.accountNumber);
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
		     action = (Action) getIntent().getSerializableExtra("Action");
		}
		
		submit.setVisibility(View.GONE);
		
		
		// Only want submit button to be visible if user has enter the correct number of digits
		accNumberText.addTextChangedListener(new TextWatcher() {

			   @Override    
			   public void onTextChanged(CharSequence s, int start,
			     int before, int count) {
			      if(s.length() != 10){
			    	  submit.setVisibility(View.GONE);
			      }
			      else{
			    	  submit.setVisibility(View.VISIBLE);
			      }
			   }

			@Override
			public void afterTextChanged(Editable arg0) {
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				
			}
			  });
		
		submit.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 

	             double accNumber = Double.parseDouble(accNumberText.getText().toString()); 
	             
	             boolean validAccount = true;
	             String clientName = "";
	             
	             // hacky user check
	             if (accNumber == 1112223333){
	            	 clientName = "Geneva";
	             }
	             else if(accNumber == 1122331122){
	            	 clientName = "Adam";
	             }
	             else if(accNumber == 1231231231){
	            	 clientName = "Salina";
	             }
	             else{
	            	 validAccount = false;
	             }
	        	 
	             
	             if (validAccount){
	            	 
	            	 Client currentClient = new Client(accNumber, clientName);
	            	 Intent intent = null; 
		        	 switch (action){
			        	 case WITHDRAW: intent = new Intent(getBaseContext(), Withdraw.class); break;
			        	 case DEPOSIT: intent = new Intent(getBaseContext(), Deposit.class);break;
			        	 case HISTORY: intent = new Intent(getBaseContext(), BalanceHistory.class);break;
			        	 case TRANSFER: intent = new Intent(getBaseContext(), Transfer.class);break;
		        	 }
		        	 
		        	 intent.putExtra("CurrentClient", currentClient);
		        	 startActivity(intent);
		        		 
	             }
	             else{
	            	 Toast.makeText(getApplicationContext(), "Please enter a valid account number.", Toast.LENGTH_LONG).show();
	             }
	         }

	      });
		
		
	}
}
