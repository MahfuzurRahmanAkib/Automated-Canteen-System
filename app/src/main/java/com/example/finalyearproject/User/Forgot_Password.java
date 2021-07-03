package com.example.finalyearproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalyearproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity implements View.OnClickListener {

    private EditText reset_username_ET;
    private Button reset_button;
    private ProgressBar progressBar;
    Typeface typeface_1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        reset_button = findViewById(R.id.reset_button_Id);
        progressBar = findViewById(R.id.progressbar_Id);
        reset_username_ET = findViewById(R.id.reset_username_Id);

        typeface_1 = Typeface.createFromAsset(getAssets(), "font/action_Man_Bold.ttf");
        reset_button.setTypeface(typeface_1);

        reset_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reset_button_Id) {
            resetPassword();
        }
    }

    private void resetPassword() {
        String email = reset_username_ET.getText().toString().trim();

        //checking the validity of the email
        if (email.isEmpty()) {
            reset_username_ET.setError("Enter an Email Address");
            reset_username_ET.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            reset_username_ET.setError("Enter a valid Email Address");
            reset_username_ET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(Forgot_Password.this, "Check Your Email to Reset Password", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Forgot_Password.this, "Try Again! Something Wrong Happened", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}