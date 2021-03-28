package com.example.finalyearproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalyearproject.model.ReviewModel;
import com.example.finalyearproject.adapter.ReviewAdapter;
import com.example.finalyearproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Check_Feedback extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button deleteFeedback;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("User_review");

    private ReviewAdapter adapter;
    private ArrayList<ReviewModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__check__feedback);

        this.setTitle("Check FeedBack");

        deleteFeedback = findViewById(R.id.delete_review_Id);

        recyclerView = findViewById(R.id.recycler_review_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new ReviewAdapter(this, list);
        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ReviewModel reviewModel = dataSnapshot.getValue(ReviewModel.class);
                    list.add(reviewModel);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        deleteFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance().getReference().child("User_review");
                databaseReference.removeValue();*/

                Toast.makeText(Admin_Check_Feedback.this,"Kaj Baki Ase",Toast.LENGTH_SHORT).show();
            }
        });
    }

}