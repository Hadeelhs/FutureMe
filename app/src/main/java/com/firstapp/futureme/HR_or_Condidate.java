package com.firstapp.futureme;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HR_or_Condidate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_or_condidate);

        ImageView hrImageView = findViewById(R.id.imageView5);
        ImageView candidateImageView = findViewById(R.id.imageView6);

        hrImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });

        candidateImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCandidateLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        Intent intent = new Intent(HR_or_Condidate.this, Login.class);
        startActivity(intent);
    }

    private void startCandidateLoginActivity() {
        Intent intent = new Intent(HR_or_Condidate.this, LoginCondidate.class);
        startActivity(intent);
    }
}

