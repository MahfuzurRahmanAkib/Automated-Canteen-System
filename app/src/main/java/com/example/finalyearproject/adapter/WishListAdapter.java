package com.example.finalyearproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.Admin.Admin_Activity;
import com.example.finalyearproject.R;
import com.example.finalyearproject.model.WishListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder> {

    ArrayList<WishListModel> w_Model;
    Context context;

    CharSequence text;
    int duration = Toast.LENGTH_SHORT;

    public WishListAdapter(ArrayList<WishListModel> w_Model, Context context) {
        this.w_Model = w_Model;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_wishlist_admin, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WishListModel model = w_Model.get(position);

        holder.text.setText(model.getText());

        //delete
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("User_wishlist").orderByChild("text")
                        .equalTo(model.text).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();

                            Toast toast = Toast.makeText(context, "Wish Deleted", duration);
                            toast.show();

                            Intent intent = new Intent(context, Admin_Activity.class);
                            context.startActivity(intent);
                        }
                        notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast toast = Toast.makeText(context, "No : "+error, duration);
                        toast.show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return w_Model.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.wishlist_text_Id);
            delete = itemView.findViewById(R.id.delete_wish_Id);
        }
    }
}
