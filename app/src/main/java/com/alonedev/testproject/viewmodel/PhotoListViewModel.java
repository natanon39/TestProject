package com.alonedev.testproject.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alonedev.testproject.Constants;
import com.alonedev.testproject.model.PhotoModel;
import com.alonedev.testproject.network.APIService;
import com.alonedev.testproject.network.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoListViewModel extends ViewModel {

    private final MutableLiveData<List<PhotoModel>> photoModels;

    public PhotoListViewModel() {
        photoModels = new MutableLiveData<>();
    }

    public MutableLiveData<List<PhotoModel>> getPhotoListObserver() {
        return photoModels;
    }

    public void makeApiCall() {
        APIService apiService = RetrofitService.getRetrofitClient(Constants.getBase_url()).create(APIService.class);
        Call<List<PhotoModel>> call = apiService.contributor("albums", "1", "photos");
        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<PhotoModel>> call, @NotNull Response<List<PhotoModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                photoModels.postValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<PhotoModel>> call, @NotNull Throwable t) {
                photoModels.postValue(null);
            }
        });
    }

}
