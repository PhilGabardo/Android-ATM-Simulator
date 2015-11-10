package com.example.a2_4hc2;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Transfer extends Activity {
	
	RadioButton chequingToSavings;
	Client currentClient;
	DialogInterface.OnClickListener dialogClickListener;
	EditText transferAmount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transfer);
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
		     currentClient = (Client) getIntent().getSerializableExtra("CurrentClient");
		}
		
		TextView chequingBalanceLabel = (TextView) findViewById(R.id.chequing_balance_label);
		TextView savingsBalanceLabel = (TextView) findViewById(R.id.savings_balance_label);
		
		Button backButton = (Button) findViewById(R.id.transfer_back_button);
		Button submitButton = (Button) findViewById(R.id.transfer_submit);
		transferAmount = (EditText) findViewById(R.id.transfer_amount);
		chequingToSavings = (RadioButton) findViewById(R.id.chequing_to_savings);
		chequingBalanceLabel.setText("Current Chequing Balance: " + String.valueOf(currentClient.chequing.getBalance()));
		savingsBalanceLabel.setText("Current Savings Balance: " + String.valueOf(currentClient.savings.getBalance()));
		
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
		
		backButton.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
		        	 Intent intent = new Intent(getBaseContext(), MainMenu.class); 
		        	 intent.putExtra("CurrentClient", currentClient);
		        	 startActivity(intent);
	         }

	      });
		
		submitButton.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 double amountToTransfer;
	        	 	if(transferAmount.getText().toString().matches("")){
	        	 		 Toast.makeText(getApplicationContext(), "Please enter a value greater than $0.00 you wish to Transfer",
		        				 Toast.LENGTH_LONG).show();
	        	 	}
	        	 	
	        	 	else{
	        	 	
	        	amountToTransfer = Double.parseDouble(transferAmount.getText().toString());
	        	 
	        	 if (chequingToSavings.isChecked()){
	        		 if (amountToTransfer > currentClient.chequing.getBalance()){
	        			 Toast.makeText(getApplicationContext(), "We're sorry. " +
		        			 		"You only have "+ String.valueOf(currentClient.chequing.getBalance()) + 
		        			 		" to withdraw from your chequing account.", Toast.LENGTH_LONG).show();
	        		 }
	        		 else{
	        			 currentClient.transfer(true, amountToTransfer);
	        			 AlertDialog.Builder builder = new AlertDialog.Builder(Transfer.this);

	        			 builder.setCancelable(false);
	        			 builder.setMessage("You transferred $" + amountToTransfer +
		         			 		" from your chequing account to savings account.\n\nDo you want to perform another task?").setPositiveButton("Yes", dialogClickListener)

		     			     	.setNegativeButton("No", dialogClickListener).show();
	        		 }
	        	 }
	        	 else{
	        		 if (amountToTransfer > currentClient.savings.getBalance()){
	        			 Toast.makeText(getApplicationContext(), "We're sorry. " +
		        			 		"You only have "+ String.valueOf(currentClient.savings.getBalance()) + 
		        			 		" to withdraw from your savings account.", Toast.LENGTH_LONG).show();
	        		 }
	        		 else{
	        			 currentClient.transfer(false, amountToTransfer);
	        			 AlertDialog.Builder builder = new AlertDialog.Builder(Transfer.this);

	        			 builder.setCancelable(false);
	        			 builder.setMessage("You transferred $" + amountToTransfer +
		         			 		" from your savings account to your chequings account.\n\nDo you want to perform another task?").setPositiveButton("Yes", dialogClickListener)
		     			     	.setNegativeButton("No", dialogClickListener).show();
	        		 }
	        	 }
	        	 	}  	 
	         }

	      });
		
		
		
		
	}

}
