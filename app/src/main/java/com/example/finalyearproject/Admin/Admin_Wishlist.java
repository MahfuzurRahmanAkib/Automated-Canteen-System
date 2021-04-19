package com.example.finalyearproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalyearproject.MainActivity;
import com.example.finalyearproject.R;
import com.example.finalyearproject.User.User_Login;
import com.example.finalyearproject.adapter.WishListAdapter;
import com.example.finalyearproject.model.WishListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Wishlist extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button deleteWish;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("User_wishlist");

    private WishListAdapter adapter;
    private ArrayList<WishListModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__wishlist);

        this.setTitle("Wish List from Users");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        deleteWish = findViewById(R.id.delete_wishlist_Id);

        recyclerView = findViewById(R.id.recycler_wishlist_Id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new WishListAdapter(list, this);
        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WishListModel wishModel = dataSnapshot.getValue(WishListModel.class);
                    list.add(wishModel);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        deleteWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.delete_wishlist_Id) {
                    DatabaseReference childNode = FirebaseDatabase.getInstance().getReference().getRoot().child("User_wishlist");
                    childNode.removeValue();

                    Intent intent = new Intent(Admin_Wishlist.this, Admin_Activity.class);
                    startActivity(intent);

                    Toast.makeText(Admin_Wishlist.this, "All The wishes deleted...!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}