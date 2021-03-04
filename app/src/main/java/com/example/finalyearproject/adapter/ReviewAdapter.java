package com.example.finalyearproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.model.ReviewModel;
import com.example.finalyearproject.R;;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    ArrayList<ReviewModel> rList;
    Context context;

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
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance().getReference();//.child(rlist.getId());
                databaseReference.removeValue();*/
                Toast.makeText(context,"A review has been deleted..!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email, text;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.review_text_Id);
            deleteButton = itemView.findViewById(R.id.review_delete_Id);
        }
    }
}
