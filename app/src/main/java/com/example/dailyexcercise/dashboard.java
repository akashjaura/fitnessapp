package com.example.dailyexcercise;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnesstracker.R;

public class dashboard extends AppCompatActivity {
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // Set the content view first

        // Find your buttons after setting the content view
        button1 = findViewById(R.id.Before_Age);
        button2 = findViewById(R.id.After_Age);

        if (button1 != null) {
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Your click listener code here
                    Intent intent = new Intent(dashboard.this, Before_Age18.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Your click listener code here
                    Intent intent = new Intent(dashboard.this, After_Age18.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
