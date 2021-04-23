package com.example.finalyearproject.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalyearproject.R;

public class Admin_Edit extends AppCompatActivity implements View.OnClickListener{
    public Button admin_canteen_picture, admin_food_List, admin_available_food;
    Typeface typeface_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit);

        this.setTitle("Edit All The Data");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        admin_canteen_picture = findViewById(R.id.admin_picture_edit);
        admin_food_List = findViewById(R.id.admin_food_list_edit);
        admin_available_food = findViewById(R.id.admin_available_food_list_edit);

        typeface_1 = Typeface.createFromAsset(getAssets(), "font/action_Man_Bold.ttf");
        admin_canteen_picture.setTypeface(typeface_1);
        admin_food_List.setTypeface(typeface_1);
        admin_available_food.setTypeface(typeface_1);

        admin_food_List.setOnClickListener(this);
        admin_canteen_picture.setOnClickListener(this);
        admin_available_food.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.admin_picture_edit) {
            Intent intent = new Intent(Admin_Edit.this, Admin_Picture_Edit.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_food_list_edit) {
            Intent intent = new Intent(Admin_Edit.this, Admin_foodList_Edit.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_available_food_list_edit) {
            Intent intent = new Intent(Admin_Edit.this, Admin_Available_Food_List_Edit.class);
            startActivity(intent);
        }
    }
}