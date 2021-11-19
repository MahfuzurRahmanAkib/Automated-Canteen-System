package com.example.finalyearproject.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyearproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class User_About_Us extends AppCompatActivity {
    private TextView textView_1, textView_2, textView_3, textView_4;

    private Typeface typeface_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);

        this.setTitle("About Us");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textView_1 = findViewById(R.id.About_Us_Id_1);
        textView_3 = findViewById(R.id.About_Us_Id_3);

        typeface_2 = Typeface.createFromAsset(getAssets(), "font/kaushanScript_Regular.otf");
        textView_1.setTypeface(typeface_2);
        textView_3.setTypeface(typeface_2);

    }
}