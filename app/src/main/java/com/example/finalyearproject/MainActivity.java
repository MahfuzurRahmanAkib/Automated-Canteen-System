package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearproject.Admin.Admin_Login;
import com.example.finalyearproject.User.Create_Account;
import com.example.finalyearproject.User.User_Login;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button user_Button, admin_Button;
    private AlertDialog.Builder alertDialogBuilder;
    private TextView register, login_TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        user_Button = findViewById(R.id.user_account_Id);
        register = findViewById(R.id.register_Id);
        admin_Button = findViewById(R.id.admin_account_Id);

        admin_Button.setOnClickListener(this);
        user_Button.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_account_Id) {
            Intent intent = new Intent(MainActivity.this, User_Login.class);
            startActivity(intent);

            Toast.makeText(MainActivity.this, "User LogIn Account", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.admin_account_Id) {
            Intent intent = new Intent(MainActivity.this, Admin_Login.class);
            startActivity(intent);

            Toast.makeText(MainActivity.this, "Admin LogIn Account", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.register_Id) {
            Intent intent = new Intent(MainActivity.this, Create_Account.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Create a New Account", Toast.LENGTH_SHORT).show();
        }
    }

    //alert dialog builder
    public void onBackPressed() {
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle("Exit Menu");
        alertDialogBuilder.setMessage("Do You Want to Exit ?");
        alertDialogBuilder.setIcon(R.drawable.ic_exit_24);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
