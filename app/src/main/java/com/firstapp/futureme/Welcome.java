package com.firstapp.futureme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ImageView imageView = findViewById(R.id.imageView);

        // Set an OnClickListener to the ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start your next activity here
                Intent intent = new Intent(Welcome.this, HR_or_Condidate.class);
                startActivity(intent);
                finish(); // Optional, to prevent the user from returning to the Welcome Activity when pressing back
            }
        });
    }
}