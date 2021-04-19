package com.example.finalyearproject.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalyearproject.R;

public class Admin_Available_Food_List_Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__available__food__list__edit);

        this.setTitle("Edit Available Food List");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}