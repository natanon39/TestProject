package com.alonedev.testproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alonedev.testproject.R;
import com.alonedev.testproject.model.PhotoModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.MyViewHolder> {
    private final Context context;
    private List<PhotoModel> photoLists;
    private final ItemClickListener clickListener;

    public PhotoListAdapter(Context context, List<PhotoModel> photoLists,ItemClickListener clickListener) {
        this.context = context;
        this.photoLists = photoLists;
        this.clickListener=clickListener;
    }

    public void setPhotoLists(List<PhotoModel> photoLists)
    {
        this.photoLists=photoLists;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PhotoListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_list, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhotoListAdapter.MyViewHolder holder, final int position) {
        holder.photoTitle.setText(photoLists.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClickCard(photoLists.get(position));
            }
        });

        GlideUrl url = new GlideUrl(photoLists.get(position).getThumbnailUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        Glide.with(context).load(url).fitCenter().into(holder.photoImage);
    }

    @Override
    public int getItemCount() {
        if (photoLists != null) return photoLists.size();
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView photoTitle;
        ImageView photoImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImage=itemView.findViewById(R.id.photo_image_id);
            photoTitle=itemView.findViewById(R.id.photo_title_id);
        }
    }

    public interface ItemClickListener
    {
        void onClickCard(PhotoModel photoModel);
    }
}
