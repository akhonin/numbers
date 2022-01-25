package com.telmone.akhonin.retrofit;

import com.telmone.akhonin.model.NumberResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiRequest {
    @Headers({"Content-Type: application/json"})
    @GET("{number}/{type}")
    Call<NumberResponse> getNumber(
            @Path("number") String number,
            @Path("type") String type
    );
    @Headers({"Content-Type: application/json"})
    @GET("{day}/{month}/date")
    Call<NumberResponse> getDate(
            @Path("day") Integer day,
            @Path("month") Integer month
    );
}
