package com.example.studentdetail;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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



import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class View_Screen_Gymkhana extends Activity{
	
	TextView tv1, tv2, tv3, tv4, tv5,tv6;
	String fname, lname,  rollno, branch, mobile, year,details,edittext, desig, por_in;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_screen);
         
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        
        Intent i = getIntent();
        // getting attached intent data
        details = i.getStringExtra("details");
        
        edittext = details.substring(0, details.indexOf(" "));
        
        
        AsyncTaskRunner runner = new AsyncTaskRunner();
		runner.execute();
        
    }
    
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(
						"http://abhirkmv.hostei.com/gymkhana.php");
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

			// parsing data

			try {
				JSONArray jArray = new JSONArray(result);
				JSONObject json_data = null;

				for (int j = 0; j < jArray.length(); j++) {
					json_data = jArray.getJSONObject(j);
					fname = json_data.getString("firstname");
					lname = json_data.getString("lastname");
					branch = json_data.getString("branch");
					rollno = json_data.getString("rollno");
					mobile = json_data.getString("mobile");
					year = json_data.getString("year");
					desig= json_data.getString("designation");
					por_in= json_data.getString("por_in");
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

		ProgressDialog dialog = new ProgressDialog(View_Screen_Gymkhana.this);
		protected void onPreExecute() {

			dialog.setMessage("Please wait...");
		       dialog.show();
		}
		
		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation
			
			if (dialog.isShowing()) {
	            dialog.dismiss();
	        }
			
			tv1.setText("Details Found: ");
			tv2.setText("Name: "+" "+" "+" "+" "+" "+" "+" "+" "+" " + fname+" " + lname);
			tv3.setText("Roll No.: "+" "+" "+" "+" " +" "+" "+ rollno);
			tv4.setText("Batch: "+" "+" "+" "+" "+" "+" "+" "+" " +year +" " +"year" +", "+" "+branch);
			
			tv5.setText("Mobile No.: "+" "+" " + mobile);
			tv6.setText("Designation "+" "+" " + desig + "," + por_in);
			
		}

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}