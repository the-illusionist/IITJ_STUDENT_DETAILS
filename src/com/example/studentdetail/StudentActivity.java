package com.example.studentdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


 
public class StudentActivity extends Activity {
     /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_layout);
        
        
        /**
         * Creating all buttons instances
         * */
     
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        
        /**
         * Handling all button click events
         * */
         
        // Listening to News Feed button click
        btn1.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), StudentDetailsActivity.class);
                startActivity(i);
            }
        });
         
       // Listening Friends button click
        btn2.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), StudentGymkhanaDetailsActivity.class);
                startActivity(i);
            }
        });
         
        // Listening Messages button click
        btn3.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), ImportantContactsActivity.class);
                startActivity(i);
            }
        });
         
        // Listening to Places button click
        btn4.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), BusScheduleActivity.class);
                startActivity(i);
            }
        });
         
        // Listening to Events button click
        btn5.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), UpcomingEventsActivity.class);
                startActivity(i);
            }
        });
         
       
         
    }
}