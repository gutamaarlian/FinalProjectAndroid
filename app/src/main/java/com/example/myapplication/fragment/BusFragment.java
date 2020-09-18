package com.example.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BustListAdapter;
import com.example.myapplication.entity.Bus;
import com.example.myapplication.utils.BusUtil;
import com.example.myapplication.utils.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BusFragment extends Fragment {

    BusUtil busUtil;
    SessionManager sessionManager;
    RecyclerView recyclerView;
    Activity activity;

    public BusFragment(Activity activity) {
        // Required empty public constructor
        busUtil = new BusUtil();
        this.activity = activity;
        sessionManager = new SessionManager(activity.getApplicationContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bus, container, false);

        recyclerView = view.findViewById(R.id.rv_bus);
        busUtil.getBus().getAllBus(busUtil.convertToParam(sessionManager.getToken().getAgencyId()))
                .enqueue(new Callback<List<Bus>>() {
                    @Override
                    public void onResponse(Call<List<Bus>> call, Response<List<Bus>> response) {
                        List<Bus> busList = response.body();
                        BustListAdapter bustListAdapter = new BustListAdapter(busList);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((activity.getApplicationContext()));
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.addItemDecoration(new DividerItemDecoration(activity.getApplicationContext(), LinearLayoutManager.VERTICAL));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        bustListAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(bustListAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Bus>> call, Throwable t) {

                    }
                });
        // Inflate the layout for this fragment
        return view;

    }
}