package com.example.myapplication.Helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import com.example.myapplication.Login;

import java.time.Duration;

public class CustomActivity {
    private Activity activity;
    private long duration = 100L;

    public CustomActivity(Activity activity) {
        this.activity = activity;
    }

    public CustomActivity(Activity activity, long duration) {
        this.activity = activity;
        this.duration = duration;
    }

    public void startAndDestroy(Class<?> route ){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                activity.startActivity(new Intent(activity.getApplicationContext(), route));
                activity.finish();
            }

        }, duration);
    }

    public void start(Class<?> route ){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                activity.startActivity(new Intent(activity.getApplicationContext(), route));
            }
        }, duration);
    }
}
