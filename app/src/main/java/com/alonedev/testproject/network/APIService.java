package com.alonedev.testproject.network;

import com.alonedev.testproject.model.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    //dynamic path
    @GET("/{path1}/{path2}/{path3}")
    Call<List<PhotoModel>> contributor(
            @Path("path1") String path1,
            @Path("path2") String path2,
            @Path("path3") String path3
    );
}