package com.example.finalyearproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class User_Login extends AppCompatActivity implements View.OnClickListener {
    EditText user_username_ET, user_password_ET;
    TextView reset_password_ET;
    Button user_create_Button;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);

        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        user_password_ET = findViewById(R.id.user_password_Id);
        reset_password_ET = findViewById(R.id.reset_password_Id);
        user_username_ET = findViewById(R.id.user_username_Id);
        progressBar = findViewById(R.id.progressbar_Id);
        user_create_Button = findViewById(R.id.user_login_button_Id);

        user_create_Button.setOnClickListener(this);
        reset_password_ET.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_login_button_Id) {
            userLogin();
        } else if (v.getId() == R.id.reset_password_Id) {
            Intent intent = new Intent(User_Login.this, Forgot_Password.class);
            startActivity(intent);
        }
    }

    private void userLogin() {
        String email = user_username_ET.getText().toString().trim();
        String password = user_password_ET.getText().toString().trim();

        //checking the validity of the email
        if (email.isEmpty()) {
            user_username_ET.setError("Enter an Email Address");
            user_username_ET.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            user_username_ET.setError("Enter a valid Email Address");
            user_username_ET.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            user_password_ET.setError("Enter a Password");
            user_password_ET.requestFocus();
            return;
        }
        if (password.length() < 6) {
            user_password_ET.setError("Password should be 6 Character");
            user_password_ET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
//Remember The User
                    SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email_Key", email);
                    editor.putString("password_Key", password);
                    editor.commit();


                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), User_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}