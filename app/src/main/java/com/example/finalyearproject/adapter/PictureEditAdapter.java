package com.example.finalyearproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearproject.R;
import com.example.finalyearproject.model.PictureEditModel;
import com.example.finalyearproject.model.PictureModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PictureEditAdapter extends RecyclerView.Adapter<PictureEditAdapter.MyViewHolder> {
    private Context context;
    private List<PictureEditModel> pictureList;

    public PictureEditAdapter(Context context, List<PictureEditModel> pictureList) {
        this.context = context;
        this.pictureList = pictureList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_picture, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodName;
        ImageView foodPicture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.canteen_foodName_Id);
            foodPicture = itemView.findViewById(R.id.canteen_foodPictures_Id);
        }
    }
}
