package com.example.studentdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentdetail.R;
 
public class CalendarActivity extends Activity {
     /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        Button btn1 = (Button) findViewById(R.id.button1);
        
        btn1.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), HolidayActivity.class);
                startActivity(i);
            }
        });
    }
}