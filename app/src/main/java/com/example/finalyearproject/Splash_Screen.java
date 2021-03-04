
package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.finalyearproject.Admin.Admin_Activity;
import com.example.finalyearproject.User.User_Activity;

public class Splash_Screen extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textView_1;
    private Typeface typeface_1;
    int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        progressBar = findViewById(R.id.progressBarID);

        setContentView(R.layout.activity_splash__screen);

        progressBar = findViewById(R.id.progressBarID);
        textView_1 = findViewById(R.id.textView_1_Id);

        //custom font
        typeface_1 = Typeface.createFromAsset(getAssets(), "font/alexBrush_Regular.ttf");
        textView_1.setTypeface(typeface_1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();
    }

    //progress bar
    public void doWork() {
        for (progress = 20; progress <= 100; progress = progress + 30) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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