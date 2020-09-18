package com.example.myapplication.service;

import com.example.myapplication.entity.Agency;
import com.example.myapplication.entity.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AgencyService {
    @POST("agency/createAgency")
    public Call<Agency> createAgency(@Body Agency agencyInp);

    @Multipart
    @POST("agency/getAgencyById")
    Call<Agency> getAgencyById(@Part("agencyId") RequestBody agencyId);
}
