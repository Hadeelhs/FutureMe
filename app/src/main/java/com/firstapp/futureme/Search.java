package com.firstapp.futureme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.os.Bundle;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ImageView mobile = findViewById(R.id.imageView4);
        ImageView web = findViewById(R.id.imageView10);
        ImageView cyber = findViewById(R.id.imageView3);
        ImageView design = findViewById(R.id.imageView9);

        // Create a single click listener for all image views
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDisplayResultsActivity();
            }
        };

        // Attach the click listener to all image views
        mobile.setOnClickListener(clickListener);
        web.setOnClickListener(clickListener);
        cyber.setOnClickListener(clickListener);
        design.setOnClickListener(clickListener);
    }

    private void startDisplayResultsActivity() {
        Intent intent = new Intent(Search.this, DisplayResults.class);
        startActivity(intent);
    }
}
