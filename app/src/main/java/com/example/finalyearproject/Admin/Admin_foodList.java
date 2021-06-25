package com.example.finalyearproject.Admin;

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
import com.example.finalyearproject.User.FeedBack;
import com.example.finalyearproject.User.User_Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Admin_foodList extends AppCompatActivity implements View.OnClickListener {
    EditText price, food, description;
    Button saveButton;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Regular_Food");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_food_list);

        price = findViewById(R.id.regular_price_Id);
        food = findViewById(R.id.regular_food_Id);
        description = findViewById(R.id.regular_description_Id);
        saveButton = findViewById(R.id.regular_save_Id);

        this.setTitle("Regular Food List");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String regularName = food.getText().toString().trim();
        String regularPrice = price.getText().toString().trim();
        String regularDescription = description.getText().toString().trim();

        if (regularName.isEmpty()) {
            food.setError("Enter Food Name");
            food.requestFocus();
            return;
        } else if (regularPrice.isEmpty()) {
            price.setError("Enter Price");
            price.requestFocus();
            return;
        } else if (regularDescription.isEmpty()) {
            description.setError("Enter Description");
            description.requestFocus();
            return;
        }
        HashMap<String, String> regular_Food_Map = new HashMap<>();

        regular_Food_Map.put("name", regularName);
        regular_Food_Map.put("price", regularPrice);
        regular_Food_Map.put("description", regularDescription);

        root.push().setValue(regular_Food_Map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(Admin_foodList.this, Admin_Activity.class);
                    startActivity(intent);
                    Toast.makeText(Admin_foodList.this, "Data Updated successfully..!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Admin_foodList.this, " Unsuccessful..! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}