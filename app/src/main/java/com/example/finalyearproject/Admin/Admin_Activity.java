package com.example.finalyearproject.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalyearproject.MainActivity;
import com.example.finalyearproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_Activity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    Button admin_canteen_picture, admin_food_List, admin_available_food, user_FeedBack, logout_Button, wishlist_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__page);

        mAuth = FirebaseAuth.getInstance();

        this.setTitle("Admin Activity");

        admin_canteen_picture = findViewById(R.id.admin_canteen_Id);
        admin_food_List = findViewById(R.id.admin_food_list_Id);
        user_FeedBack = findViewById(R.id.admin_check_Feedback_Id);
        wishlist_Button = findViewById(R.id.admin_check_wishlist_Id);
        admin_available_food = findViewById(R.id.admin_available_food_list_Id);
        logout_Button = findViewById(R.id.admin_logout_Id);


        admin_food_List.setOnClickListener(this);
        admin_canteen_picture.setOnClickListener(this);
        admin_available_food.setOnClickListener(this);
        wishlist_Button.setOnClickListener(this);
        user_FeedBack.setOnClickListener(this);
        logout_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.admin_canteen_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_Picture.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_food_list_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_foodList.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_available_food_list_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_Available_Food_List.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_check_Feedback_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_Check_Feedback.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_check_wishlist_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_Wishlist.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_logout_Id) {
            Intent intent = new Intent(Admin_Activity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}