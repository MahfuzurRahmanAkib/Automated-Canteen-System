package com.example.finalyearproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("User_review");

    private ReviewAdapter adapter;
    private ArrayList<ReviewModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__check__feedback);

        this.setTitle("Check FeedBack");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView = findViewById(R.id.recycler_review_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new ReviewAdapter(this, list);
        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ReviewModel reviewModel = dataSnapshot.getValue(ReviewModel.class);
                    list.add(reviewModel);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu_feedback, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_feedback_menu_id) {
            DatabaseReference childNode = FirebaseDatabase.getInstance().getReference().getRoot().child("User_review");
            childNode.removeValue();

            Toast.makeText(Admin_Check_Feedback.this, "All The Feedback Deleted...!!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Admin_Check_Feedback.this, Admin_Activity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}