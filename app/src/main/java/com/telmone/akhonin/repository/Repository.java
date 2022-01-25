package com.telmone.akhonin.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.telmone.akhonin.model.NumberResponse;
import com.telmone.akhonin.retrofit.ApiRequest;
import com.telmone.akhonin.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ApiRequest apiRequest;

    public Repository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<NumberResponse> getNumbers(String number, String type) {
        final MutableLiveData<NumberResponse> data = new MutableLiveData<>();
        apiRequest.getNumber(number, type)
                .enqueue(new Callback<NumberResponse>() {
                    @Override
                    public void onResponse(Call<NumberResponse> call, Response<NumberResponse> numberResponse) {
                        if (numberResponse.body() != null) {
                            data.setValue(numberResponse.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<NumberResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
    public LiveData<NumberResponse> getDate(Integer day, Integer month) {
        final MutableLiveData<NumberResponse> data = new MutableLiveData<>();
        apiRequest.getDate(day, month)
                .enqueue(new Callback<NumberResponse>() {
                    @Override
                    public void onResponse(Call<NumberResponse> call, Response<NumberResponse> numberResponse) {
                        if (numberResponse.body() != null) {
                            data.setValue(numberResponse.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<NumberResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
