package com.example.finalyearproject.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalyearproject.R;

public class User_Picture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__picture);

        this.setTitle("Pictures Of Canteen");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}