package com.example.dailyexcercise;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnesstracker.R;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 1500;
    //    private Button move;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animations);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animations);
        //hooks
        image = findViewById(R.id.splash_image);

        slogan = findViewById(R.id.let_start);
        //assign these animation
        image.setAnimation(topAnim);

        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(MainActivity.this, loginpage.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_SCREEN);


    }
}

















































