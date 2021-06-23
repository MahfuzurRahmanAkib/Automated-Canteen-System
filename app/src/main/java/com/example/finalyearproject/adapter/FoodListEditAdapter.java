package com.example.finalyearproject.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.R;
import com.example.finalyearproject.model.FoodEditModel;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class FoodListEditAdapter extends RecyclerView.Adapter<FoodListEditAdapter.MyViewHolder> {
    ArrayList<FoodEditModel> fList;
    Context context;

    CharSequence text;
    int duration = Toast.LENGTH_SHORT;

    public FoodListEditAdapter(Context context, ArrayList<FoodEditModel> fList) {
        this.fList = fList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_foodlist_edit, parent, false);
        return new FoodListEditAdapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FoodEditModel model = fList.get(position);

        holder.name.setText(model.getname());
        holder.price.setText(model.getprice());
        holder.description.setText(model.getdescription());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.food_content))
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0, 50, 0)
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return fList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, description;
        ImageView edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.foodlist_name_Id);
            price = itemView.findViewById(R.id.foodlist_price_Id);
            description = itemView.findViewById(R.id.foodlist_description_Id);

            edit = itemView.findViewById(R.id.update_food_Id);
            delete = itemView.findViewById(R.id.delete_food_Id);
        }
    }
}

