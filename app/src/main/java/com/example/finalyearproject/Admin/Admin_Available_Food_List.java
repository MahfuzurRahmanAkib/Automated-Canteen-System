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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Admin_Available_Food_List extends AppCompatActivity implements View.OnClickListener {
    EditText A_price, A_food, A_canteen;
    Button saveButton;


    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Available_Food");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__available__food__list);

        A_canteen = findViewById(R.id.available_canteen_Id);
        A_price = findViewById(R.id.available_price_Id);
        A_food = findViewById(R.id.available_food_name_Id);
        saveButton = findViewById(R.id.available_save_Id);

        this.setTitle("Available Food List");

        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String availableName = A_food.getText().toString().trim();
        String availablePrice = A_price.getText().toString().trim();
        String availableCanteen = A_canteen.getText().toString().trim();

        if (availableCanteen.isEmpty()) {
            A_canteen.setError("Enter Description");
            A_canteen.requestFocus();
            return;
        } else if (availableName.isEmpty()) {
            A_food.setError("Enter Food Name");
            A_food.requestFocus();
            return;
        } else if (availablePrice.isEmpty()) {
            A_price.setError("Enter Price");
            A_price.requestFocus();
            return;
        }

        HashMap<String, String> available_Food_Map = new HashMap<>();

        available_Food_Map.put("canteen_Name", availableCanteen);
        available_Food_Map.put("food_name", availableName);
        available_Food_Map.put("price", availablePrice);

        root.push().setValue(available_Food_Map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Admin_Available_Food_List.this, "Data Updated successfully..!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Admin_Available_Food_List.this, Admin_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Admin_Available_Food_List.this, " Unsuccessful..! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
