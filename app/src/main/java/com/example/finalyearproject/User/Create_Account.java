package com.example.finalyearproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalyearproject.MainActivity;
import com.example.finalyearproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Create_Account extends AppCompatActivity implements View.OnClickListener {
    EditText create_username_ET, crete_password_ET;
    Button create_Button;
    private ProgressBar progressBar;
    Typeface typeface_1;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);
        this.setTitle("Create Account");

        mAuth = FirebaseAuth.getInstance();

        create_Button = findViewById(R.id.create_Id);
        progressBar = findViewById(R.id.progressbar_Id);
        create_username_ET = findViewById(R.id.create_Username_Id);
        crete_password_ET = findViewById(R.id.create_password_Id);

        typeface_1 = Typeface.createFromAsset(getAssets(), "font/action_Man_Bold.ttf");
        create_Button.setTypeface(typeface_1);

        create_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.create_Id) {
            userRegister();
        }
    }

    private void userRegister() {
        String email = create_username_ET.getText().toString().trim();
        String password = crete_password_ET.getText().toString().trim();

        //checking the validity of the email
        if (email.isEmpty()) {
            create_username_ET.setError("Enter an email address");
            create_username_ET.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            create_username_ET.setError("Enter a valid email address");
            create_username_ET.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            crete_password_ET.setError("Enter a password");
            crete_password_ET.requestFocus();
            return;
        }
        if (password.length() < 6) {
            crete_password_ET.setError("Password should be 6 Character");
            crete_password_ET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Register is Successful", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                    Intent intent = new Intent(Create_Account.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "User is Already Registered", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}