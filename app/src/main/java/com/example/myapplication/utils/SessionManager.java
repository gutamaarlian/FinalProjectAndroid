package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.myapplication.entity.JWToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class SessionManager {

    private SharedPreferences prefs;
    private Gson gson = new GsonBuilder().create();

    public SessionManager(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public boolean setSession(String tokenJWT) throws JSONException {
        try {
            JWT jwt = new JWT(tokenJWT);
            Map<String, Claim> getClaims = jwt.getClaims();
            String token = getClaims.get("iss").asString();
            Log.d("token", token);

//            JSONObject json = new JSONObject(token);
//            Log.d("json", json.getString("userId"));
//            prefs.edit().putString("userName", json.getString("userName")).apply();
//            prefs.edit().putString("agencyId", json.getString("agencyId")).apply();
//            prefs.edit().putString("userId", json.getString("userId")).apply();
            prefs.edit().putString("user", token).apply();
            return true;
        } catch (Exception e) {
            Log.d("error", e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    public String getByKey(String key) {
        String token = prefs.getString(key, "");
        return token;
    }

    public JWToken getToken() {
        return gson.fromJson(prefs.getString("user", ""), JWToken.class);
    }

    public void removeToken() {
        prefs.edit().remove("user").apply();
//        prefs.edit().remove("userName").apply();
//        prefs.edit().remove("agencyId").apply();
//        prefs.edit().remove("userEmail").apply();
    }
}