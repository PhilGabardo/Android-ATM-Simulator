package com.a24hc3;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a24hc3.Account.AccountType;
import com.a24hc3.R;
import com.a24hc3.R.id;
import com.a24hc3.R.layout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

// Withdraw money
public class Withdraw extends Activity {
	
	TextView withdrawLabel;
	Client currentClient;
	DialogInterface.OnClickListener dialogClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.withdraw);
		
		SeekBar withdrawAmount = (SeekBar) findViewById(R.id.withdrawAmount);
		withdrawLabel = (TextView) findViewById(R.id.withdrawLabelAmount);
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
		     currentClient = (Client) getIntent().getSerializableExtra("CurrentClient");
		}
		
		withdrawAmount.setProgress(20);
		withdrawAmount.incrementProgressBy(20);
		withdrawAmount.setMax(500);
		
		withdrawAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progress = progress /20;
				progress = progress*20;
				withdrawLabel.setText("$" + String.valueOf(progress));
				
			}
		});
		
		Button withdrawButton = (Button) findViewById(R.id.withdrawSubmit);
		
		Button backButton = (Button) findViewById(R.id.withdrawBack);
		
		
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
		
		withdrawButton.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 Money amountToWithdraw = new Money(Double.parseDouble((withdrawLabel.getText().toString().substring(1))));
	        	 
	        	 RadioButton chequing = (RadioButton) findViewById(R.id.chequing); 
	        	 
	        	 
	        	 if (chequing.isChecked()){
	        		 if (amountToWithdraw.amount > currentClient.chequing.getBalance().amount){
	        			 Toast.makeText(getApplicationContext(), "We're sorry. " +
	        			 		"You only have "+ currentClient.chequing.getBalance().toString() + 
	        			 		" to withdraw from.", Toast.LENGTH_LONG).show();
	        		 }
	        		 else{
	        			 
	        			Intent intent = new Intent(getBaseContext(), WithdrawSplash.class);	  
	   		        	 intent.putExtra("CurrentClient", currentClient);
	   		        	 intent.putExtra("AmountWithdrew", amountToWithdraw);
	   		        	 intent.putExtra("AccountType", AccountType.CHEQUING);
	   		        	 startActivity(intent);
	   		        	 
	        			 
	        		 }
	        	 }
	        	 else{
	        		 if (amountToWithdraw.amount > currentClient.savings.getBalance().amount){
	        			 Toast.makeText(getApplicationContext(), "We're sorry. " +
	        			 		"You only have "+ currentClient.savings.getBalance().toString() + 
	        			 		" to withdraw from.", Toast.LENGTH_LONG).show();
	        		 }
	        		 else{
	        			 
	        			 Intent intent = new Intent(getBaseContext(), WithdrawSplash.class);	  
	   		        	 intent.putExtra("CurrentClient", currentClient);
	   		        	 intent.putExtra("AmountWithdrew", amountToWithdraw);
	   		        	 intent.putExtra("AccountType", AccountType.SAVINGS);
	   		        	 startActivity(intent);
	   		        	 
	        		 }
	        		 
	        	 }
	        	 
	        	 
	         }

	      });
		
		backButton.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
		        	 Intent intent = new Intent(getBaseContext(), MainMenu.class); 
		        	 intent.putExtra("CurrentClient", currentClient);
		        	 startActivity(intent);
	         }

	      });
	}
	
	@Override
	public void onBackPressed() {
	}
	
	

}

