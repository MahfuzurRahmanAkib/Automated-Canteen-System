package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.finalyearproject.Admin.Admin_Activity;
import com.example.finalyearproject.User.User_Activity;

public class Splash_Screen extends AppCompatActivity {
    LottieAnimationView lottie;
    private TextView textView_1;
    private Typeface typeface_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView_1 = findViewById(R.id.textView_1_id);
        lottie = findViewById(R.id.lottie);

        //custom font
        typeface_1 = Typeface.createFromAsset(getAssets(), "font/andada-bold.otf");
        textView_1.setTypeface(typeface_1);

        textView_1.animate().translationY(-1400).setDuration(2700).setStartDelay(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(i);
                startApp();
            }
        }, 3500);
    }

    public void startApp() {
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("email_Key") && sharedPreferences.contains("password_Key")) {
            Intent intent = new Intent(getApplicationContext(), User_Activity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
            startActivity(intent);
        }

    }
}