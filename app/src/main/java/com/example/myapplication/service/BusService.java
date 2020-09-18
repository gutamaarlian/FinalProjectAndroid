package com.example.myapplication.service;

import com.example.myapplication.entity.Bus;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BusService {
    @Multipart
    @POST("bus/getAllBusByAgencyId")
    public Call<List<Bus>>getAllBus(@Part("agencyId")RequestBody agencyId);
}
