package com.firstapp.futureme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.os.Handler;


public class First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start your next activity here
                Intent intent = new Intent(First.this, Welcome.class);
                startActivity(intent);
                finish(); // Optional, to prevent the user from returning to the WelcomeActivity when pressing back
            }
        }, 3000);

    }

}