package com.alonedev.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.alonedev.testproject.model.PhotoModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra("Photo")) {
            PhotoModel photo = getIntent().getParcelableExtra("Photo");
            if(photo!=null) {
                setTitle(photo.getTitle());
                ImageView imageView = findViewById(R.id.photo_image);
                GlideUrl url = new GlideUrl(photo.getThumbnailUrl(), new LazyHeaders.Builder()
                        .addHeader("User-Agent", "your-user-agent")
                        .build());
                Glide.with(this).load(url).fitCenter().into(imageView);
            }
        }
    }
}