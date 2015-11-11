package com.a24hc3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.a24hc3.R.id;
import com.a24hc3.R.layout;

import android.os.Bundle;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


// View history of transactions
public class BalanceHistory extends ListActivity {
	
  Client currentClient;
  private static final String TEXT1 = "text1";
  private static final String TEXT2 = "text2";

	
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    
    Bundle b = this.getIntent().getExtras();
	if(b!=null){
	     currentClient = (Client) getIntent().getSerializableExtra("CurrentClient");
	}
    setContentView(R.layout.balance_history);
    
    TextView label = (TextView) findViewById(R.id.balance_history_label);
    
    // Display current balances and "Balance History" label
    label.setText("Current Chequing Balance : $" + currentClient.chequing.getBalance().toString() + "\n"+
    					"Current Savings Balance : $" + currentClient.savings.getBalance().toString() + "\n"
    					+ "Balance History :");
    
    // Set up adapter for list of transactions
    final ListAdapter listAdapter = createListAdapter(currentClient.transactionHistory);
    setListAdapter(listAdapter);
    
    Button backButton = (Button) findViewById(R.id.balance_history_back);
    
    backButton.setOnClickListener(new OnClickListener() {
		
        @Override
        public void onClick(View v) {
	        	 Intent intent = new Intent(getBaseContext(), MainMenu.class); 
	        	 intent.putExtra("CurrentClient", currentClient);
	        	 startActivity(intent);
        }

     });
  }

  
  
  private List<Map<String, String>> convertToListItems(final ArrayList<Transaction> transactions) {
    final List<Map<String, String>> listItem = new ArrayList<Map<String, String>>(transactions.size());
	
	for (final Transaction transaction: transactions) {
	  final Map<String, String> listItemMap = new HashMap<String, String>();
	  listItemMap.put(TEXT1,"$" + transaction.amount.toString() + " - " + transaction.transactionType.toString());
	  listItemMap.put(TEXT2, transaction.accountType.toString() + " - " + transaction.date.toString());
	  listItem.add(Collections.unmodifiableMap(listItemMap));
	}
	
	return Collections.unmodifiableList(listItem);
  }
	
  private ListAdapter createListAdapter(final ArrayList<Transaction> transactions) {
    final String[] fromMapKey = new String[] {TEXT1, TEXT2};
    final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
	final List<Map<String, String>> list = convertToListItems(transactions);

	return new SimpleAdapter(this, list, android.R.layout.simple_list_item_2, fromMapKey, toLayoutId);
		}
	 
  @Override
  public void onBackPressed() {	
  }

} 


