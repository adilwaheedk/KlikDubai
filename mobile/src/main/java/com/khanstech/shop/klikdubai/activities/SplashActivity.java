package com.khanstech.shop.klikdubai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.khanstech.shop.klikdubai.R;
import com.khanstech.shop.klikdubai.tasks.GetHomeScreenData;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //PreferenceHelper.clearAgentSession(this);
        new GetHomeScreenData(this).execute();
    }
}