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
import com.example.finalyearproject.model.PictureModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder> {

    private Context context;
    private List<PictureModel> pictureList;

    public PictureAdapter(Context context, List<PictureModel> pictureList) {
        this.context = context;
        this.pictureList = pictureList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_picture, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PictureModel model = pictureList.get(position);
        holder.foodName.setText(model.getimageName());
        Picasso.with(context)
                .load(model.getImageUrl())
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
