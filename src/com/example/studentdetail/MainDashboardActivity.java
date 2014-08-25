package com.example.studentdetail;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.studentdetail.R;
 
public class MainDashboardActivity extends Activity {
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
         
        /**
         * Creating all buttons instances
         * */
        // Dashboard News feed button
        Button btn_college = (Button) findViewById(R.id.btn_college);
         
        // Dashboard Friends button
        Button btn_calendar = (Button) findViewById(R.id.btn_calendar);
         
        // Dashboard Messages button
        Button btn_students = (Button) findViewById(R.id.btn_students);
         
        // Dashboard Places button
        Button btn_counselling = (Button) findViewById(R.id.btn_counselling);
         
        // Dashboard Events button
        Button btn_places = (Button) findViewById(R.id.btn_places);
         
        // Dashboard Photos button
        Button btn_about = (Button) findViewById(R.id.btn_about);
         
        /**
         * Handling all button click events
         * */
         
        // Listening to News Feed button click
        btn_college.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), CollegeActivity.class);
                startActivity(i);
            }
        });
         
       // Listening Friends button click
        btn_calendar.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(i);
            }
        });
         
        // Listening Messages button click
        btn_students.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), StudentActivity.class);
                startActivity(i);
            }
        });
         
        // Listening to Places button click
        btn_counselling.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), CounsellingActivity.class);
                startActivity(i);
            }
        });
         
        // Listening to Events button click
        btn_places.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), PlacesActivity.class);
                startActivity(i);
            }
        });
         
        // Listening to Photos button click
        btn_about.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
            }
        });
    }
}