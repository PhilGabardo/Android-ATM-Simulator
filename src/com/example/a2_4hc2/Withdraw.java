package com.example.a2_4hc2;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Withdraw extends Activity {
	
	TextView withdrawLabel;
	Client currentClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.withdraw);
		
		SeekBar withdrawAmount = (SeekBar) findViewById(R.id.withdrawAmount);
		withdrawLabel = (TextView) findViewById(R.id.withdrawLabel);
		
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
				
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progress = progress /20;
				progress = progress*20;
				withdrawLabel.setText("$" + String.valueOf(progress));
				// TODO Auto-generated method stub
				
			}
		});
		
		Button withdrawButton = (Button) findViewById(R.id.withdrawSubmit);
		
		withdrawButton.setOnClickListener(new OnClickListener() {
			
	         @Override
	         public void onClick(View v) {
	        	 int amountToWithdraw = Integer.parseInt(withdrawLabel.getText().toString().substring(1));
	        	 
	        	 RadioButton chequing = (RadioButton) findViewById(R.id.chequing); 
	        	 
	        	 
	        	 if (currentClient)
	        	 
	        	 
	         }

	      });
	}

}

