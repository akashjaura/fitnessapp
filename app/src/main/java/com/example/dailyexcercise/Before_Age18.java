package com.example.dailyexcercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fitnesstracker.R;

public class Before_Age18 extends AppCompatActivity {
    int[] newArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_age18);
        newArray=new int[]{
          R.id.Back,R.id.Bicycle_crunches,R.id.Crunches,R.id.eccentric_leg_raise,R.id.eccentric_pose,
                R.id.flat_dumbell_press,R.id.jump_squat,R.id.lateral_squat,R.id.lunges,R.id.plank,R.id.side_raise,R.id.feet_elevated_mountain_climber,
        };
    }
    public void ImageButtonClicked(View view){

        for(int i = 0;i <  newArray.length;i++){
            if(view.getId()==newArray[i]){
                int value=i+1;
                Log.i("FIRST",String.valueOf(value));

                Intent intent=new Intent(Before_Age18.this,display.class);
                intent.putExtra("value",String.valueOf(value));
                startActivity(intent);

            }
        }
    }
}