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

import com.example.finalyearproject.Admin.Admin_Activity;
import com.example.finalyearproject.Admin.Admin_Edit;
import com.example.finalyearproject.Admin.Admin_Save;
import com.example.finalyearproject.R;
import com.example.finalyearproject.model.AvailableFoodEditModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AvailableFoodEditAdapter extends RecyclerView.Adapter<AvailableFoodEditAdapter.MyViewHolder> {

    ArrayList<AvailableFoodEditModel> a_f_List;
    Context context;

    CharSequence text;
    int duration = Toast.LENGTH_SHORT;

    public AvailableFoodEditAdapter(Context context, ArrayList<AvailableFoodEditModel> a_f_List) {
        this.context = context;
        this.a_f_List = a_f_List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_available_foodlist_edit, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AvailableFoodEditModel model = a_f_List.get(position);

        holder.canteen_Name.setText(model.getcanteen_Name());
        holder.food_name.setText(model.getfood_name());
        holder.price.setText(model.getPrice());

        //delete
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("Available_Food").orderByChild("food_name")
                        .equalTo(model.food_name).addListenerForSingleValueEvent(new ValueEventListener() {
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
                        .setContentHolder(new ViewHolder(R.layout.available_edit_content))
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0, 50, 0)
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View holderView = (LinearLayout) dialog.getHolderView();

                EditText canteen_Name = holderView.findViewById(R.id.available_Canteen_name_Id);
                EditText price = holderView.findViewById(R.id.available_Price_Id);
                EditText food_Name = holderView.findViewById(R.id.available_food_name_Id);

                canteen_Name.setText(model.getcanteen_Name());
                price.setText(model.getPrice());

                Button edit = holderView.findViewById(R.id.update_available_food_Id);

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("canteen_Name", canteen_Name.getText().toString());
                        map.put("price", price.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Available_Food").orderByChild("food_name")
                                .equalTo(model.food_name).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                                    appleSnapshot.getRef().updateChildren(map);
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
        return a_f_List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView canteen_Name, food_name, price;
        ImageView edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            canteen_Name = itemView.findViewById(R.id.available_Canteen_name_Id);
            food_name = itemView.findViewById(R.id.available_food_name_Id);
            price = itemView.findViewById(R.id.available_Price_Id);

            edit = itemView.findViewById(R.id.update_available_food_Id);
            delete = itemView.findViewById(R.id.delete_available_food_Id);

        }
    }
}
