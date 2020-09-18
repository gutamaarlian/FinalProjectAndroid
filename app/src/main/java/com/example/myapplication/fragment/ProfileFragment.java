package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Helper.CustomActivity;
import com.example.myapplication.Login;
import com.example.myapplication.R;
import com.example.myapplication.entity.Agency;
import com.example.myapplication.entity.User;
import com.example.myapplication.utils.AgencyUtil;
import com.example.myapplication.utils.SessionManager;
import com.example.myapplication.utils.UserUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


    UserUtil userUtil;
    private SessionManager sessionManager;
    TextView txtName, txtEmail, txtPhone;

    public ProfileFragment(Context context) {
        this.sessionManager = new SessionManager(context);
        this.userUtil = new UserUtil();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtName = view.findViewById(R.id.txt_profile_name);
        txtEmail = view.findViewById(R.id.txt_email);
        txtPhone = view.findViewById(R.id.txt_phone);

        userUtil.getUser().getUserById(userUtil.convertToParam(sessionManager.getToken().getUserId()))
                .enqueue(new Callback<User>(){
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        txtName.setText(user.getFirstName() + " " +user.getLastName());
                        txtEmail.setText(user.getEmail());
                        txtPhone.setText(user.getMobileNumber());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

        return view;
    }

    public void logoutClick(View view){
        sessionManager.removeToken();
        new CustomActivity(getActivity()).startAndDestroy(Login.class);
    }
}