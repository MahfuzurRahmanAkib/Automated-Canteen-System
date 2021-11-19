package com.example.finalyearproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

public class Admin_Offer extends AppCompatActivity implements View.OnClickListener {
    EditText o_food, o_new_price, o_old_price;
    Button save_Offer_Button;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Offer_Food");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__offer);

        o_food = findViewById(R.id.offer_food_Id);
        o_new_price = findViewById(R.id.offer_new_Id);
        o_old_price = findViewById(R.id.offer_old_Id);
        save_Offer_Button = findViewById(R.id.offer_save_Id);

        this.setTitle("Save Today's Offer");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        save_Offer_Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String offerName = o_food.getText().toString().trim();
        String offer_old_price = o_old_price.getText().toString().trim();
        String offer_new_price = o_new_price.getText().toString().trim();

        if (offerName.isEmpty()) {
            o_food.setError("Enter Food name");
            o_food.requestFocus();
            return;
        } else if (offer_old_price.isEmpty()) {
            o_old_price.setError("Enter Old Price");
            o_old_price.requestFocus();
            return;
        } else if (offer_new_price.isEmpty()) {
            o_new_price.setError("Enter New Price");
            o_new_price.requestFocus();
            return;
        }

        HashMap<String, String> available_Food_Map = new HashMap<>();

        available_Food_Map.put("food_name", offerName);
        available_Food_Map.put("old_price", offer_old_price);
        available_Food_Map.put("new_price", offer_new_price);

        root.push().setValue(available_Food_Map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(Admin_Offer.this, Admin_Activity.class);
                    startActivity(intent);
                    Toast.makeText(Admin_Offer.this, "Data Updated successfully..!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Admin_Offer.this, " Unsuccessful..! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}