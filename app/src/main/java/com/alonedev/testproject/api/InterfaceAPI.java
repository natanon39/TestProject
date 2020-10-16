package com.alonedev.testproject.api;

import com.alonedev.testproject.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceAPI {
    //dynamic path
    @GET("/{path1}/{path2}/{path3}")
    Call<List<Photo>> contributor(
            @Path("path1") String path1,
            @Path("path2") String path2,
            @Path("path3") String path3
    );
}