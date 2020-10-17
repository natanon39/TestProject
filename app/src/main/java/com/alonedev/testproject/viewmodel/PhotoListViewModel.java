package com.alonedev.testproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alonedev.testproject.network.APIService;
import com.alonedev.testproject.network.RetroInstance;
import com.alonedev.testproject.model.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoListViewModel extends ViewModel {
    private MutableLiveData<List<PhotoModel>> photoModels;

    public  PhotoListViewModel()
    {
        photoModels = new MutableLiveData<>();
    }

    public MutableLiveData<List<PhotoModel>> getPhotoListObserver()
    {
        return  photoModels;
    }

    public  void makeApiCall()
    {
        APIService apiService = RetroInstance.getRetrofitClient().create(APIService.class);
        Call<List<PhotoModel>> call = apiService.contributor("albums","1","photos");
        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                if(!response.isSuccessful())
                {
                    return;
                }
                photoModels.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                photoModels.postValue(null);
            }
        });
    }

}
