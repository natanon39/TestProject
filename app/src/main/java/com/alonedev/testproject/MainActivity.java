package com.alonedev.testproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.alonedev.testproject.adapter.PhotoListAdapter;
import com.alonedev.testproject.model.PhotoModel;
import com.alonedev.testproject.viewmodel.PhotoListViewModel;


import java.util.List;

public class MainActivity extends AppCompatActivity implements PhotoListAdapter.ItemClickListener {
    private List<PhotoModel> photoLists;
    private PhotoListAdapter photoListAdapter;
    private PhotoListViewModel photoListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Photos");

        RecyclerView recycleView = findViewById(R.id.recyclerview);
        photoListAdapter = new PhotoListAdapter(MainActivity.this, photoLists, this);
        recycleView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recycleView.setAdapter(photoListAdapter);

        photoListViewModel = new ViewModelProvider(this).get(PhotoListViewModel.class);
        photoListViewModel.getPhotoListObserver().observe(this, new Observer<List<PhotoModel>>() {
            @Override
            public void onChanged(List<PhotoModel> photoModels) {
                if (photoModels != null) {
                    photoLists = photoModels;
                    photoListAdapter.setPhotoLists(photoModels);
                } else {
                    //do something if null.
                }
            }
        });
        photoListViewModel.makeApiCall();

    }

    @Override
    public void onClickCard(PhotoModel photoModel) {
        Intent intent = new Intent(this, DetailActivity.class);

        //implement Parcelable to photomodel
        intent.putExtra("Photo", photoModel);
        startActivity(intent);
    }
}