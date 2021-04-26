package com.example.finalyearproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.R;
import com.example.finalyearproject.model.FoodEditModel;
import com.example.finalyearproject.model.FoodModel;

import java.util.ArrayList;

public class FoodListEditAdapter extends RecyclerView.Adapter<FoodListEditAdapter.MyViewHolder> {
    ArrayList<FoodEditModel> fList;
    Context context;

    public FoodListEditAdapter( Context context,ArrayList<FoodEditModel> fList) {
        this.fList = fList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_food_list, parent, false);
        return new FoodListEditAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FoodEditModel model = fList.get(position);

        holder.name.setText(model.getname());
        holder.price.setText(model.getprice());
        holder.description.setText(model.getdescription());
    }

    @Override
    public int getItemCount()  {
        return fList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.foodlist_name_Id);
            price = itemView.findViewById(R.id.foodlist_price_Id);
            description = itemView.findViewById(R.id.foodlist_description_Id);
        }
    }
}




























