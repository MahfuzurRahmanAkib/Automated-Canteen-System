package com.example.finalyearproject.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalyearproject.R;

public class Admin_Save extends AppCompatActivity implements View.OnClickListener {
    public Button admin_canteen_picture, admin_food_List, admin_available_food,admin_offer_save;
    Typeface typeface_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__save);

        this.setTitle("Save All The Data");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        admin_canteen_picture = findViewById(R.id.admin_picture);
        admin_food_List = findViewById(R.id.admin_food_list_Id);
        admin_available_food = findViewById(R.id.admin_available_food_list_Id);
        admin_offer_save = findViewById(R.id.admin_offer);

        typeface_1 = Typeface.createFromAsset(getAssets(), "font/action_Man_Bold.ttf");
        admin_canteen_picture.setTypeface(typeface_1);
        admin_food_List.setTypeface(typeface_1);
        admin_available_food.setTypeface(typeface_1);


        admin_food_List.setOnClickListener(this);
        admin_canteen_picture.setOnClickListener(this);
        admin_available_food.setOnClickListener(this);
        admin_offer_save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.admin_picture) {
            Intent intent = new Intent(Admin_Save.this, Admin_Picture.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_food_list_Id) {
            Intent intent = new Intent(Admin_Save.this, Admin_foodList.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_available_food_list_Id) {
            Intent intent = new Intent(Admin_Save.this, Admin_Available_Food_List.class);
            startActivity(intent);
        }else if (v.getId() == R.id.admin_offer) {
            Intent intent = new Intent(Admin_Save.this, Admin_Offer.class);
            startActivity(intent);
        }
    }
}