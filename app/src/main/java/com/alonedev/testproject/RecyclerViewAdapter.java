package com.alonedev.testproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alonedev.testproject.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Photo> mData;

    public RecyclerViewAdapter(Context context,List<Photo> photo)
    {
        this.mContext=context;
        this.mData=photo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.photo_title.setText(mData.get(position).getTitle());
       Picasso.with(mContext).load("via.placeholder.com/600/e9b68.png").fit().centerInside().into(holder.photo_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView photo_title;
        ImageView photo_thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo_title = itemView.findViewById(R.id.photo_title_id);
            photo_thumbnail = itemView.findViewById(R.id.photo_image_id);
        }

        public void setItem(int position) {

        }
    }
}
