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
import com.example.finalyearproject.model.PictureEditModel;
import com.example.finalyearproject.model.PictureModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PictureEditAdapter extends RecyclerView.Adapter<PictureEditAdapter.MyViewHolder> {
    private Context context;
    private List<PictureEditModel> pictureList;

    CharSequence text;
    int duration = Toast.LENGTH_SHORT;

    public PictureEditAdapter(Context context, List<PictureEditModel> pictureList) {
        this.context = context;
        this.pictureList = pictureList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_picture_edit, parent, false);
        return new PictureEditAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PictureEditModel model = pictureList.get(position);
        holder.foodName.setText(model.getImageName());
        Picasso.with(context)
                .load(model.getImageUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(holder.foodPicture);

        //delete
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("UploadImage").orderByChild("imageName")
                        .equalTo(model.imageName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot : snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();

                            Intent intent = new Intent(context, Admin_Edit.class);
                            context.startActivity(intent);

                            Toast toast = Toast.makeText(context, "Data Deleted", duration);
                            toast.show();
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
        return pictureList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodName;
        ImageView foodPicture, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.canteen_foodName_Id);
            foodPicture = itemView.findViewById(R.id.canteen_foodPictures_Id);
            delete = itemView.findViewById(R.id.delete_image_Id);
        }
    }
}
