package com.alonedev.testproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.alonedev.testproject.api.InterfaceAPI;
import com.alonedev.testproject.model.Photo;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Photo> photos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Photos");
        parseJSON();
    }


    private void parseJSON()//use retrofit to request and get data.
    {
        String baseurl = "https://jsonplaceholder.typicode.com/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI holderApi=retrofit.create(InterfaceAPI.class);
        Call<List<Photo>> call = holderApi.contributor("albums","1","photos");
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(!response.isSuccessful())
                {
                    return;
                }
                photos = response.body();
                RecyclerView recycleView = findViewById(R.id.recyclerview);
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,photos);
                recycleView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                recycleView.setAdapter(recyclerViewAdapter);
            }
            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }
}