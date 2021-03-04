package com.example.finalyearproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalyearproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedBack extends AppCompatActivity implements View.OnClickListener {
    EditText feedback_Username, feedback_EditText;
    Button feedback_Submit_Button;


    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("User_review");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed_back);
        this.setTitle("User FeedBack");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        feedback_EditText = findViewById(R.id.feedback_EditText_Id);
        feedback_Submit_Button = findViewById(R.id.feedback_submit_button_Id);

        feedback_Submit_Button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String text = feedback_EditText.getText().toString().trim();


        if (text.isEmpty()) {
            feedback_EditText.setError("Enter review");
            feedback_EditText.requestFocus();
            return;
        }

        HashMap<String, String> review_Map = new HashMap<>();

        review_Map.put("text", text);

        root.push().setValue(review_Map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FeedBack.this, "Review send successfully..!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FeedBack.this, User_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(FeedBack.this, " Unsuccessful..! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



























