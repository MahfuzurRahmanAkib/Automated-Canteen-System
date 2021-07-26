package com.example.finalyearproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class User_Wishlist extends AppCompatActivity {
    private Button wishlist_submit;
    private EditText wishlist_editText;
    private TextView textView;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("User_wishlist");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__wishlist);

        wishlist_editText = findViewById(R.id.wishlist_EditText_Id);
        wishlist_submit = findViewById(R.id.wishlist_submit_button_Id);
        textView = findViewById(R.id.text_wish_Id);

        this.setTitle("User Wish List");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        wishlist_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = wishlist_editText.getText().toString().trim();

                if (text.isEmpty()) {
                    wishlist_editText.setError("Enter your wish");
                    wishlist_editText.requestFocus();
                    return;
                }

                HashMap<String, String> wish_Map = new HashMap<>();

                wish_Map.put("text", text);

                root.push().setValue(wish_Map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(User_Wishlist.this, "Your wish send successfully..!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(User_Wishlist.this, User_Activity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(User_Wishlist.this, " Unsuccessful..! Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}