package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.entity.Agency;
import com.example.myapplication.entity.User;
import com.example.myapplication.utils.AgencyUtil;
import com.example.myapplication.utils.SessionManager;
import com.example.myapplication.utils.UserUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyFragment extends Fragment {

    AgencyUtil agencyUtil;
    private SessionManager sessionManager;

    public AgencyFragment(Context context) {
        this.sessionManager = new SessionManager(context);
        this.agencyUtil = new AgencyUtil();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        agencyUtil.getAgency().getAgencyById(agencyUtil.convertToParam(sessionManager.getToken().getAgencyId()))
                .enqueue(new Callback<Agency>() {
                    @Override
                    public void onResponse(Call<Agency> call, Response<Agency> response) {
                        ((TextView) getActivity().findViewById(R.id.txt_agency_name)).setText(response.body().getName());
                        ((TextView) getActivity().findViewById(R.id.txt_details)).setText(response.body().getDetails());
                    }

                    @Override
                    public void onFailure(Call<Agency> call, Throwable t) {
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agency, container, false);
    }
}