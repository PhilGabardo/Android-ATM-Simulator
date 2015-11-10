package com.example.a2_4hc2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class BalanceHistory extends ListActivity {
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2" };
    
    setContentView(R.layout.balance_history);
    
    
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
  }
  
  
  public enum UsState {
	    DE("Delaware"),
	    PA("Pennsylvania"),
	    NJ("New Jersey");

	       private String stateName;

	    private UsState(final String name) {
	        this.stateName = name;
	    }

	    public String getStateName() {
	return this.stateName;
	    }

	    public String getAbbreviation() {
	return this.name();
	    }

	}
  

	private static final String TEXT1 = "text1";
	private static final String TEXT2 = "text2";

	private List<Map<String, String>> convertToListItems(final UsState[] states) {
	    final List<Map<String, String>> listItem =
	      new ArrayList<Map<String, String>>(states.length);
	
	    for (final UsState state: states) {
	        final Map<String, String> listItemMap = new HashMap<String, String>();
	listItemMap.put(TEXT1, state.getStateName());
	listItemMap.put(TEXT2, state.getAbbreviation());
	listItem.add(Collections.unmodifiableMap(listItemMap));
	    }
	
	    return Collections.unmodifiableList(listItem);
	}
	
	 private ListAdapter createListAdapter(final UsState[] states) {
		    final String[] fromMapKey = new String[] {TEXT1, TEXT2};
		    final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
		    final List<Map<String, String>> list = convertToListItems(states);

		    return new SimpleAdapter(this, list,
		                          android.R.layout.simple_list_item_2,
		                             fromMapKey, toLayoutId);
		}

} 


