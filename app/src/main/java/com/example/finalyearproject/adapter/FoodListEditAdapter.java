package com.example.finalyearproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.Admin.Admin_Edit;
import com.example.finalyearproject.R;
import com.example.finalyearproject.model.FoodEditModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Regular_Food").orderByChild("name")
                        .equalTo(model.name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();

                            Toast toast = Toast.makeText(context, "Data Deleted", duration);
                            toast.show();

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

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.food_content))
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0, 50, 0)
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View holderView = (LinearLayout) dialog.getHolderView();

                EditText price = holderView.findViewById(R.id.regular_price_Id);
                EditText description = holderView.findViewById(R.id.regular_description_Id);
                EditText name = holderView.findViewById(R.id.foodlist_name_Id);

                description.setText(model.getdescription());
                price.setText(model.getprice());

                Button edit = holderView.findViewById(R.id.update_regular_food_Id);

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("description", description.getText().toString());
                        map.put("price", price.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Regular_Food").orderByChild("name")
                                .equalTo(model.name).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot snapshot2 : snapshot.getChildren()) {
                                    snapshot2.getRef().updateChildren(map);
                                    notifyDataSetChanged();

                                    Toast toast = Toast.makeText(context, "Data Updated Successfully", duration);
                                    toast.show();

                                    Intent intent = new Intent(context, Admin_Edit.class);
                                    context.startActivity(intent);

                                }
                                dialog.dismiss();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
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

