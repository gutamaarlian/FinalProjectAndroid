package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Helper.CustomActivity;
import com.example.myapplication.fragment.AgencyFragment;
import com.example.myapplication.fragment.BusFragment;
import com.example.myapplication.fragment.ProfileFragment;
import com.example.myapplication.utils.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Button btnLogout;
    TextView txtHello;
    SessionManager sessionManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sessionManager = new SessionManager(this);
        bottomNavigationView = findViewById(R.id.nav_bot_tab);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.tab_profile);
    }

    public void logoutClick(View v){
        sessionManager.removeToken();
        new CustomActivity(this).startAndDestroy(Login.class);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;

        switch (item.getItemId()){
            case R.id.tab_profile:

                fragment = new ProfileFragment(getApplicationContext());
                break;
            case R.id.tab_bus:
                fragment = new BusFragment(this);
                break;
            case R.id.tab_agency :
                fragment = new AgencyFragment(getApplicationContext());
                break;
            default:
                fragment = new ProfileFragment(getApplicationContext());
                break;
        }
        return loadFragment(fragment);
    }



    public boolean loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
        return true;
    }




}