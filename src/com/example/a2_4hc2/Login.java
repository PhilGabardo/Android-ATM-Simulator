package com.example.a2_4hc2;

import com.example.a2_4hc2.MainMenu.Action;

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
		
		submit.setClickable(false);
		
		accNumberText.addTextChangedListener(new TextWatcher() {

			   @Override    
			   public void onTextChanged(CharSequence s, int start,
			     int before, int count) {
			      if(s.length() != 10)
			          submit.setClickable(false);
			      else
			    	  submit.setClickable(true);
			   }

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			  });
		
		submit.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 

	             int accNumber = Integer.parseInt(accNumberText.getText().toString()); 
	             
	             boolean validAccount = true;
	             String clientName = "";
	             
	             
	             
	             switch (accNumber){
	             	default: validAccount = false; break;
	             	case 1112223333: clientName = "Geneva"; break;
	             	case 1231231231: clientName = "Adam"; break;
	             	case 1122331122: clientName = "Salina"; break;
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
