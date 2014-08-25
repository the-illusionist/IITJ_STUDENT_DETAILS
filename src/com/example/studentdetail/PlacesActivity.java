package com.example.studentdetail;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.studentdetail.R;
 
public class PlacesActivity extends Activity {
     /** Called when the activity is first created. */
	
	ListView listView;
	
	ArrayList<String> itemsList = new ArrayList<String>();

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_layout);
       
		listView = (ListView) findViewById(R.id.listview);
		
		itemsList = new ArrayList<String>();
		itemsList.add("RESTAURANTS");
		itemsList.add("MOVIE THEATRES");
		itemsList.add("IMPORTANT SHOPS");
		itemsList.add("PLACES TO VISIT");
		itemsList.add("BUS SERVICES");
		itemsList.add("DISTANCES BETWEEN");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				PlacesActivity.this,
				R.layout.list_row, R.id.textView1,
				itemsList);
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// selected item
				//String details = ((TextView) view.findViewById(R.id.textView1)).getText().toString();

				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						View_Places.class);
				// sending data to new activity
				//i.putExtra("details", details);
				startActivity(i);

			}
		});
    }
}
