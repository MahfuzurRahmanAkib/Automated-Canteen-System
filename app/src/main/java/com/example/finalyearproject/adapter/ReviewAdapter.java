package com.example.finalyearproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.Admin.Admin_Activity;
import com.example.finalyearproject.Admin.Admin_Edit;
import com.example.finalyearproject.model.ReviewModel;
import com.example.finalyearproject.R;;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    ArrayList<ReviewModel> rList;
    Context context;

    CharSequence text;
    int duration = Toast.LENGTH_SHORT;

    public ReviewAdapter(Context context, ArrayList<ReviewModel> rList) {
        this.rList = rList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_review_admin, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ReviewModel reviewModel = rList.get(position);

        holder.text.setText(reviewModel.getText());

        //delete
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("User_review").orderByChild("text")
                        .equalTo(reviewModel.text).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();

                            Toast toast = Toast.makeText(context, "Review Deleted", duration);
                            toast.show();

                            Intent intent = new Intent(context, Admin_Activity.class);
                            context.startActivity(intent);
                        }
                        notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email, text;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.review_text_Id);
            delete = itemView.findViewById(R.id.delete_review_Id);
        }
    }
}
