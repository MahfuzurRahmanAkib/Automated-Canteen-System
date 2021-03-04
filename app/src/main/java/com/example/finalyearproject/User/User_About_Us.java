package com.example.finalyearproject.User;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyearproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class User_About_Us extends AppCompatActivity {
    private TextView textView_1, textView_2, textView_3, textView_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);

        this.setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textView_1 = findViewById(R.id.About_Us_Id_1);
        textView_2 = findViewById(R.id.About_Us_Id_2);
        textView_3 = findViewById(R.id.About_Us_Id_3);
        textView_4 = findViewById(R.id.About_Us_Id_4);

    }
}