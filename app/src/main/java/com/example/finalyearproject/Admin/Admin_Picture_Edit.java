package com.example.finalyearproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.finalyearproject.R;
import com.example.finalyearproject.User.User_Picture;
import com.example.finalyearproject.adapter.PictureAdapter;
import com.example.finalyearproject.adapter.PictureEditAdapter;
import com.example.finalyearproject.model.PictureEditModel;
import com.example.finalyearproject.model.PictureModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Picture_Edit extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PictureEditAdapter pictureEditAdapter;
    private ArrayList<PictureEditModel> pictureEditModelList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__picture__edit);

        this.setTitle("Edit Pictures");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recycler_user_picture_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pictureEditModelList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("UploadImage");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PictureEditModel model = dataSnapshot.getValue(PictureEditModel.class);
                    pictureEditModelList.add(model);
                }
                pictureEditAdapter = new PictureEditAdapter(Admin_Picture_Edit.this,pictureEditModelList);
                recyclerView.setAdapter(pictureEditAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(),"Error : "+error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}