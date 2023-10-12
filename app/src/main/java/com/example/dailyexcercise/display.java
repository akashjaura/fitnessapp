package com.example.dailyexcercise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitnesstracker.R;

public class display extends AppCompatActivity {
  String button_value;
  Button startBtn;
  private CountDownTimer countDownTimer;
  TextView textView;
  private boolean TimeRunning;
  private long TimeLeft;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent=getIntent();
        button_value=intent.getStringExtra("value");
        int int_value=Integer.valueOf(button_value);
        switch (int_value){
            case 1:
                setContentView(R.layout.activity_back_exercise);
                break;
            case 2:
                setContentView(R.layout.activity_bicycle_crunches);
                break;
            case 3:
                setContentView(R.layout.activity_crunch);
                break;
            case 4:
                setContentView(R.layout.activity_eccentric_leg_raise);
                break;
            case 5:
                setContentView(R.layout.activity_eccentric_pose);
                break;
            case 6:
                setContentView(R.layout.activity_flat_dumbell);
                break;
            case 7:
                setContentView(R.layout.activity_jump_squat);
                break;
            case 8:
                setContentView(R.layout.activity_lateral_sqaut);
                break;
            case 9:
                setContentView(R.layout.activity_lunges);
                break;
            case 10:
                setContentView(R.layout.activity_plank);
                break;
            case 11:
                setContentView(R.layout.activity_side_raise);
                break;
            case 12:
                setContentView(R.layout.activity_feet_elevated);
                break;

        }
        startBtn=findViewById(R.id.start_button);
        textView=findViewById(R.id.timer);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TimeRunning){
                        stopTimer();
                }
                else{
                    startTimer();
                }
            }
        });


    }
    private void stopTimer(){

        countDownTimer.cancel();
        TimeRunning=false;
        startBtn.setText("Start");
    }
    private void startTimer() {
        final CharSequence value1 = textView.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0, 2);
        String num3 = num1.substring(3, 5);
        final int number = Integer.valueOf(num2) * 60 + Integer.valueOf(num3);
        TimeLeft = number * 1000;
        countDownTimer = new CountDownTimer(TimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                int newvalue = Integer.valueOf(button_value) + 1;
                if (newvalue <= 7) {
                    Intent intent = new Intent(display.this, display.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newvalue));
                    startActivity(intent);
                } else {
                    newvalue = 1;
                    Intent intent = new Intent(display.this, display.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(newvalue));
                    startActivity(intent);
                }

            }



    }.start();
        startBtn.setText("PAUSE");
        TimeRunning=true;
    }
    private void updateTimer() {
        int minutes = (int) TimeLeft / 60000;
        int seconds = (int) (TimeLeft % 60000) / 1000;
        String TimeLeftText = "";

        if (minutes < 10) {
            TimeLeftText = "0";
        }

        TimeLeftText = TimeLeftText + minutes + ":";

        if (seconds < 10) {
            TimeLeftText += "0";
        }

        TimeLeftText += seconds;
        textView.setText(TimeLeftText);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}