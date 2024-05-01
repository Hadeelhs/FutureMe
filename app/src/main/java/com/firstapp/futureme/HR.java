package com.firstapp.futureme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;

public class HR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr);
        ImageView archive = findViewById(R.id.imageView7);
        ImageView job = findViewById(R.id.imageView8);

        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });

        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCandidateLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        Intent intent = new Intent(HR.this, Archive.class);
        startActivity(intent);
    }

    private void startCandidateLoginActivity() {
        Intent intent = new Intent(HR.this, Post_job.class);
        startActivity(intent);
    }
}
