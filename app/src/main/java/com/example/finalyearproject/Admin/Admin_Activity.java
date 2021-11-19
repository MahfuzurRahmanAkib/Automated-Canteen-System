package com.example.finalyearproject.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalyearproject.MainActivity;
import com.example.finalyearproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_Activity extends AppCompatActivity implements View.OnClickListener {

    Button user_FeedBack, logout_Button, wishlist_Button, admin_save_Button, admin_edit_Button;
    Typeface typeface_1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__page);

        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        user_FeedBack = findViewById(R.id.admin_check_Feedback_Id);
        wishlist_Button = findViewById(R.id.admin_check_wishlist_Id);
        logout_Button = findViewById(R.id.admin_logout_Id);
        admin_save_Button = findViewById(R.id.admin_save_Id);
        admin_edit_Button = findViewById(R.id.admin_edit_Id);

        typeface_1 = Typeface.createFromAsset(getAssets(), "font/action_Man_Bold.ttf");
        user_FeedBack.setTypeface(typeface_1);
        logout_Button.setTypeface(typeface_1);
        wishlist_Button.setTypeface(typeface_1);
        admin_save_Button.setTypeface(typeface_1);
        admin_edit_Button.setTypeface(typeface_1);

        user_FeedBack.setOnClickListener(this);
        admin_save_Button.setOnClickListener(this);
        wishlist_Button.setOnClickListener(this);
        logout_Button.setOnClickListener(this);
        admin_edit_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.admin_save_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_Save.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_check_wishlist_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_Wishlist.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_edit_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_Edit.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_logout_Id) {
            Intent intent = new Intent(Admin_Activity.this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.admin_check_Feedback_Id) {
            Intent intent = new Intent(Admin_Activity.this, Admin_Check_Feedback.class);
            startActivity(intent);
        }
    }
}