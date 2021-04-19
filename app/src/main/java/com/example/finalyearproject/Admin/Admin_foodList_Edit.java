package com.example.finalyearproject.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalyearproject.R;

public class Admin_foodList_Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food_list__edit);

        this.setTitle("EditFood List");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}