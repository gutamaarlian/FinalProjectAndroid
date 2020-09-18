package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Helper.CustomActivity;
import com.example.myapplication.entity.Agency;
import com.example.myapplication.entity.User;
import com.example.myapplication.utils.AgencyUtil;
import com.example.myapplication.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    UserUtil userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        userService = new UserUtil();
    }

    public void registerClick(View view) {
        String inpFirstName = ((EditText) findViewById(R.id.inp_firstName)).getText().toString();
        String inpLastName = ((EditText) findViewById(R.id.inp_lastName)).getText().toString();
        String inpEmail = ((EditText) findViewById(R.id.inp_email)).getText().toString();
        String inpPassword = ((EditText) findViewById(R.id.inp_password)).getText().toString();
        String inpMobileNumber = ((EditText) findViewById(R.id.inp_mobileNumber)).getText().toString();
        String inpRePassword = ((EditText) findViewById(R.id.inp_password2)).getText().toString();
        String agencyName = ((EditText) findViewById(R.id.inp_agencyName)).getText().toString();
        String agencyDetails = ((EditText) findViewById(R.id.inp_agencyDetail)).getText().toString();




        if (!checkInput(inpFirstName, inpLastName, inpPassword, inpMobileNumber, inpRePassword, agencyName, agencyDetails, inpEmail)) {
            Toast.makeText(getApplicationContext(), "SEMUA FIELD HARUS DIISI !", Toast.LENGTH_LONG).show();
        } else {
            User user = new User(inpFirstName, inpLastName, inpEmail, inpPassword, inpMobileNumber);
            if (!isEmailValid(inpEmail)) {
                Toast.makeText(getApplicationContext(), "FORMAT EMAIL TIDAK VALID !", Toast.LENGTH_LONG).show();
            } else {
                if (matchPassword(inpPassword, inpRePassword)) {
                    this.checkEmail(user);
                } else {
                    Toast.makeText(getApplicationContext(), "PASSWORD AJA GK COCOK, APALAGI PERASAAN :(", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public boolean checkInput(String ... editTexts) {
        for (String editText : editTexts) {
            if (editText.equals("")) return false;
        }
        return true;
    }

    public boolean matchPassword(String password, String rePassword) {
        return password.equals(rePassword);
    }

    public void checkEmail(User user) {
        userService.getUser().checkEmail(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResp = response.body();
                if (userResp.getId() != null) {
                    Toast.makeText(getApplicationContext(), "EMAIL SUDAH TERPAKAI COBA EMAIL LAIN", Toast.LENGTH_LONG).show();
                } else {
                    doRegister(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void doRegister(User user) {
        userService.getUser().register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                createAgency(response);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void createAgency(Response<User> response) {
        String agencyName = ((EditText) findViewById(R.id.inp_agencyName)).getText().toString();
        String agencyDetails = ((EditText) findViewById(R.id.inp_agencyDetail)).getText().toString();
        User user = response.body();
        Agency agency = new Agency();
        agency.setUserId(user.getId());
        agency.setName(agencyName);
        agency.setDetails(agencyDetails);
        AgencyUtil agencyUtil = new AgencyUtil();
        agencyUtil.getAgency().createAgency(agency).enqueue(new Callback<Agency>() {
            @Override
            public void onResponse(Call<Agency> call, Response<Agency> response) {
            new CustomActivity(Register.this).startAndDestroy(Login.class);
            }

            @Override
            public void onFailure(Call<Agency> call, Throwable t) {

            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void loginClick(View v){

        new CustomActivity(this).startAndDestroy(Login.class);

    }



}
