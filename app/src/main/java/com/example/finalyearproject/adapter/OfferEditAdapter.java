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

import com.example.finalyearproject.Admin.Admin_Edit;
import com.example.finalyearproject.R;
import com.example.finalyearproject.model.OfferEditModel;
import com.example.finalyearproject.model.OfferModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OfferEditAdapter extends RecyclerView.Adapter<OfferEditAdapter.MyViewHolder> {
    ArrayList<OfferEditModel> offer_List;
    Context context;


    public OfferEditAdapter(Context context, ArrayList<OfferEditModel> offer_List) {
        this.context = context;
        this.offer_List = offer_List;
    }


    @NonNull
    @Override
    public OfferEditAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_offer_edit, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferEditAdapter.MyViewHolder holder, int position) {
        OfferEditModel model = offer_List.get(position);

        holder.food_name.setText(model.getfood_name());
        holder.old_price.setText(model.getold_price());
        holder.new_price.setText(model.getnew_price());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Offer_Food").orderByChild("food_name")
                        .equalTo(model.food_name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();

                            Intent intent = new Intent(context, Admin_Edit.class);
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
        return offer_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView food_name, old_price, new_price;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            food_name = itemView.findViewById(R.id.offer_food_name_Id);
            old_price = itemView.findViewById(R.id.offer_old_price_Id);
            new_price = itemView.findViewById(R.id.offer_new_price_Id);
            delete = itemView.findViewById(R.id.delete_offer_Id);

        }
    }
}
