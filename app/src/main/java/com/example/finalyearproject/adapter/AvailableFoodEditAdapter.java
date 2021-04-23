package com.example.finalyearproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.R;
import com.example.finalyearproject.model.AvailableFoodEditModel;
import com.example.finalyearproject.model.AvailableFoodModel;

import java.util.ArrayList;

public class AvailableFoodEditAdapter extends RecyclerView.Adapter<AvailableFoodEditAdapter.MyViewHolder> {

    ArrayList<AvailableFoodEditModel> a_f_List;
    Context context;

    public AvailableFoodEditAdapter(Context context,ArrayList<AvailableFoodEditModel> a_f_List) {
        this.context = context;
        this.a_f_List = a_f_List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_available_food_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AvailableFoodEditModel model = a_f_List.get(position);

        holder.canteen_Name.setText(model.getcanteen_Name());
        holder.food_name.setText(model.getfood_name());
        holder.price.setText(model.getPrice());

    }

    @Override
    public int getItemCount() {
        return a_f_List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView canteen_Name, food_name, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            canteen_Name = itemView.findViewById(R.id.available_Canteen_name_Id);
            food_name = itemView.findViewById(R.id.available_food_name_Id);
            price = itemView.findViewById(R.id.available_Price_Id);

        }
    }
}
