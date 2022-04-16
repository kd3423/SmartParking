package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Gson gson = new Gson();
        Users u1 = gson.fromJson(getIntent().getStringExtra("user_obj"), Users.class);

    }
}