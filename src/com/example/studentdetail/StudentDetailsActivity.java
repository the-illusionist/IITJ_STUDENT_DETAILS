package com.example.studentdetail;

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

public class StudentDetailsActivity extends Activity {
	Button button;
	TextView tv1, tv2;
	EditText et;

	String fname, lname, rollno, branch, mobile, year;

	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	ListView listView;
	int $num;

	ArrayList<String> itemsList = new ArrayList<String>();

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_details);
		listView = (ListView) findViewById(R.id.listview);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		et = (EditText) findViewById(R.id.editText1);
		button = (Button) findViewById(R.id.btn);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Connection_Detector cd = new Connection_Detector(getApplicationContext());
				if (et.getText().toString().equals("")) {
					Toast.makeText(StudentDetailsActivity.this,
							"Field is empty", Toast.LENGTH_SHORT).show();
				} else if (et.getText().toString().equals(" ")) {
					Toast.makeText(StudentDetailsActivity.this,
							"Field is empty", Toast.LENGTH_SHORT).show();
				} else if (cd.isConnectingToInternet())
				// true or false
				{
					AsyncTaskRunner runner = new AsyncTaskRunner();
					runner.execute();
				}
				else {
					showAlertDialog(StudentDetailsActivity.this,
							"No Internet Connection",
							"No internet connection.", false);
				}

			}
		});
	}

	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);


		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	private class AsyncTaskRunner extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			String edittext = et.getText().toString();
			try {

				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost("http://abhirkmv.hostei.com/");
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
					branch = json_data.getString("branch");
					rollno = json_data.getString("rollno");
					mobile = json_data.getString("mobile");
					year = json_data.getString("year");
					itemsList.add(rollno + " " + " " + " " + fname + " "
							+ lname);
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

		ProgressDialog dialog = new ProgressDialog(StudentDetailsActivity.this);

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
					StudentDetailsActivity.this,
					R.layout.list_row, R.id.textView1,
					itemsList);
			tv1.setText("Matches Found:");
			listView.setAdapter(adapter);

			listView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					// selected item
					String details = ((TextView) view.findViewById(R.id.textView1)).getText().toString();

					// Launching new Activity on selecting single List Item
					Intent i = new Intent(getApplicationContext(),
							View_Screen.class);
					// sending data to new activity
					i.putExtra("details", details);
					startActivity(i);

				}
			});

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
