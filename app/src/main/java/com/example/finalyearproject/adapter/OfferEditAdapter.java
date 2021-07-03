package com.example.finalyearproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.R;
import com.example.finalyearproject.model.OfferEditModel;
import com.example.finalyearproject.model.OfferModel;

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
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView food_name, old_price, new_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            food_name = itemView.findViewById(R.id.offer_food_name_Id);
            old_price = itemView.findViewById(R.id.offer_old_price_Id);
            new_price = itemView.findViewById(R.id.offer_new_price_Id);
        }
    }
}
