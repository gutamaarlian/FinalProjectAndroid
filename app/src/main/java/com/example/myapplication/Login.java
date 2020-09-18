package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Helper.CustomActivity;
import com.example.myapplication.utils.SessionManager;
import com.example.myapplication.utils.UserUtil;

import org.json.JSONException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        sessionManager = new SessionManager(this);
        if(!sessionManager.getByKey("user").equals("")){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    public void loginClick(View v) {
        String inpEmail = ((EditText) findViewById(R.id.inp_email)).getText().toString();
        String inpPassword = ((EditText) findViewById(R.id.inp_password)).getText().toString();
        RequestBody requestBodyEmail = RequestBody.create(
                MediaType.parse("text/plain"), inpEmail
        );
        RequestBody requestBodyPassword = RequestBody.create(
                MediaType.parse("text/plain"), inpPassword
        );
        (new UserUtil()).getUser().login(requestBodyEmail, requestBodyPassword).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if(sessionManager.setSession(response.body())){
                        Toast.makeText(Login.this, sessionManager.getToken().getUserId(), Toast.LENGTH_SHORT).show();
                        new CustomActivity(Login.this).startAndDestroy(MainActivity.class);
//                        Intent intent = new Intent(Login.this, MainActivity.class);
//                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email atau Password Salah", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.d("error2", "");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("error :", t.getMessage());
            }
        });
    }

    public void registerClick(View view){
        new CustomActivity(this).start(Register.class);
    }
}