package com.example.finalyearproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.finalyearproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_Login extends AppCompatActivity implements View.OnClickListener {

    private EditText adminUserName, adminPassword;
    private Button admin_login;
    private ProgressBar progressBar;
    private TextView admin_TextView;
    private int counter = 2;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = db.getReference().child("Admins");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__login);

        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        admin_login = findViewById(R.id.admin_login_Id);
        adminPassword = findViewById(R.id.admin_password_Id);
        admin_TextView = findViewById(R.id.admin_login_textView_Id);
        adminUserName = findViewById(R.id.admin_Username_Id);
        progressBar = findViewById(R.id.progressbar_Id);
        admin_TextView.setText("Attempt remaining =2 times");

        admin_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     //getting password and implement
        String username = adminUserName.getText().toString();
        String password = adminPassword.getText().toString();

      if (username.equals("admin") && password.equals("1234")) {

            SharedPreferences sharedPreferences = getSharedPreferences("adminDetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username_Key", username);
            editor.putString("password_Key", password);
            editor.commit();

            Intent intent2 = new Intent(Admin_Login.this, Admin_Activity.class);
            startActivity(intent2);

        } else {
            counter--;
            admin_TextView.setText("Number of Attempt Remaining = " + counter + " times");
            if (counter == 0) {
                admin_login.setEnabled(false);
            }
        }
    }
}