package com.example.studentdetail;

import java.util.ArrayList;
import java.util.List;









import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CounsellingActivity extends Activity implements OnItemSelectedListener{
    
	Button button;
	ListView listView;
	String item;
	
	String fname, lname, rollno, email,home, mobile, year,desig;

	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	
	int $num;

	ArrayList<String> itemsList = new ArrayList<String>();

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counselling_layout);
        
        
        button = (Button) findViewById(R.id.btn);
         listView = (ListView) findViewById(R.id.listview);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Co-ordinator");
        categories.add("Assistant_Co-ordinator");
        categories.add("Student_Guide");
      
        
        // Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
		
		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
		
		/* button on click starts */
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Connection_Detector cd = new Connection_Detector(getApplicationContext());
				 if (cd.isConnectingToInternet())
				// true or false
				{
					AsyncTaskRunner runner = new AsyncTaskRunner();
					runner.execute();
				}
				else {
					showAlertDialog(CounsellingActivity.this,
							"No Internet Connection",
							"No internet connection.", false);
				}

			}
		});/* button on click ends */
    }
    
    @Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// On selecting a spinner item
		item = parent.getItemAtPosition(position).toString();
		
		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*Alert Box Starts */
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		// alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
	/*Alert Box Ends */
	
	/*Asyc Task runner starts*/
	
	private class AsyncTaskRunner extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			String edittext = item;
			try {

				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost("http://abhirkmv.hostei.com/counselling.php");
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("input", edittext));
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse response = client.execute(post);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();

			} catch (Exception e) {
				Log.e("log_tag", "Error in http connection" + e.toString());
			}
			// convert response to string
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				sb = new StringBuilder();
				sb.append(reader.readLine() + "\n");
				String line = "0";

				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

				is.close();
				result = sb.toString();

			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}

			// paring data

			try {
				JSONArray jArray = new JSONArray(result);
				// JSONObject json_data = null;
				itemsList = new ArrayList<String>();
				$num = jArray.length();
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					fname = json_data.getString("firstname");
					lname = json_data.getString("lastname");
					email = json_data.getString("email");
					rollno = json_data.getString("rollno");
					mobile = json_data.getString("mobile");
					year = json_data.getString("year");
					desig= json_data.getString("designation");
					home= json_data.getString("home");
					itemsList.add(rollno + " " + " " + " " + fname + " "
							+ lname );
				}

			} catch (JSONException e1) {
				e1.printStackTrace();
				// Toast.makeText(getBaseContext(), "No item Found",
				// Toast.LENGTH_LONG).show();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			return null;

		}

		ProgressDialog dialog = new ProgressDialog(CounsellingActivity.this);

		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					CounsellingActivity.this,
					R.layout.list_row, R.id.textView1,
					itemsList);
			
			listView.setAdapter(adapter);

			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					// selected item
					String details =  ((TextView) view.findViewById(R.id.textView1)).getText().toString();

					// Launching new Activity on selecting single List Item
					Intent i = new Intent(getApplicationContext(),
							View_Screen_Counselling.class);
					// sending data to new activity
					i.putExtra("details", details);
					startActivity(i);

				}
			});

		}

	}
	
	/* Asyntask runner ends*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}