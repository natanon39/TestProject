package com.alonedev.testproject;

import com.alonedev.testproject.model.PhotoModel;
import com.alonedev.testproject.network.APIService;
import com.alonedev.testproject.network.RetrofitService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APITest {
    APIService apiService;
    MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        apiService = RetrofitService
                .getRetrofitClient(server.url("/").toString()).create(APIService.class);
    }

    @Test
    public void testLoadPhotoList_ShouldNotNull() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("[{}]"));
        Response<List<PhotoModel>> actual = apiService.contributor("path1", "path2", "path3").execute();
        Assert.assertNotNull(actual.body());
        Assert.assertEquals(200, actual.code());
    }


    //Use Retrofit
    public static <T> T createRetrofitService(String host, Class<T> classTarget) throws Exception {
        return new Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(classTarget);
    }
}



