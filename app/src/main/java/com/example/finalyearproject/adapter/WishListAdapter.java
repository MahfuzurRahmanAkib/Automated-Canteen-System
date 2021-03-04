package com.example.finalyearproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.R;
import com.example.finalyearproject.model.WishListModel;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder> {

    ArrayList<WishListModel> w_Model;
    Context context;

    public WishListAdapter(ArrayList<WishListModel> w_Model, Context context) {
        this.w_Model = w_Model;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.sample_wishlist_admin,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WishListModel model = w_Model.get(position);

        holder.text.setText(model.getWishText());

    }

    @Override
    public int getItemCount() {
        return w_Model.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text= itemView.findViewById(R.id.wishlist_text_Id);
        }
    }
}
